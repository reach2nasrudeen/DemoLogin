package lea.madebyfire.com.demologin.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import lea.madebyfire.com.demologin.R;
import lea.madebyfire.com.demologin.base.BaseActivity;
import lea.madebyfire.com.demologin.interfaces.viewResponseDelegates.LoginViewDelegate;
import lea.madebyfire.com.demologin.interfaces.viewResponseDelegates.MessageViewType;
import lea.madebyfire.com.demologin.utils.SessionManager;
import lea.madebyfire.com.demologin.viewModels.LoginViewModel;

public class LoginActivity extends BaseActivity implements LoginViewDelegate {

    private Context mContext;

    private Button btnLogin;
    private Button btnRegister;

    private EditText textPhone;
    private EditText textPassword;

    private Toolbar toolbar;

    private ProgressDialog dialog;

    private LoginViewModel loginViewModel;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = new LoginViewModel(this);
        mContext = this;
        sessionManager = getApp().getUserPreference();
        if(sessionManager.getUserLoginStatus()) {
            initActivity(new Intent(mContext, HomeActivity.class));
        } else {
            initDialog();
            initView();
            setupListener();
            initToolbar();
        }

    }

    public void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
    }

    private void initDialog() {
        dialog = new ProgressDialog(mContext);
        dialog.setProgressStyle(android.R.attr.progressBarStyleSmall);
        dialog.setMessage(getString(R.string.loading));
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
    }

    private void initView() {
        textPhone = (EditText) findViewById(R.id.textPhone);
        textPassword = (EditText) findViewById(R.id.textPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);
    }

    private void setupListener() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginViewModel.setPhoneNumber(textPhone.getText().toString());
                loginViewModel.setPassword(textPassword.getText().toString());
                loginViewModel.onLogin();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginViewModel.onRegister();
            }
        });
    }

    @Override
    public void launchHomeScreen() {
        sessionManager.setUserLoginStatus(true);
        initActivity(new Intent(mContext, HomeActivity.class));
    }

    @Override
    public void launchRegister() {
        initActivity(new Intent(this, RegisterActivity.class));
    }

    @Override
    public void showErrorMessage(int errorMessage, MessageViewType viewType) {
        if (viewType == MessageViewType.Toast) {
            Toast.makeText(mContext, errorMessage, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showErrorMessage(String errorMessage, MessageViewType viewType) {
        if (viewType == MessageViewType.Toast) {
            Toast.makeText(mContext, errorMessage, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showSuccessMessage(int message, MessageViewType viewType) {
        // No implementation
    }

    @Override
    public void showProgressView(Boolean show) {
        if (dialog != null) {
            if (show) {
                dialog.show();
            } else {
                dialog.dismiss();
            }
        }
    }

    private void initActivity(Intent intent) {
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        finish();
    }

    private Boolean exit = false;

    @Override
    public void onBackPressed() {
        checkExit();
    }


    public void checkExit() {
        if (exit) {
            finish(); // finish activity
        } else {
            Toast.makeText(this, getString(R.string.press_again_to_exit),
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);
        }
    }
}
