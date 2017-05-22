package lea.madebyfire.com.demologin.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import lea.madebyfire.com.demologin.R;
import lea.madebyfire.com.demologin.base.BaseActivity;
import lea.madebyfire.com.demologin.interfaces.viewResponseDelegates.LoginViewDelegate;
import lea.madebyfire.com.demologin.interfaces.viewResponseDelegates.MessageViewType;
import lea.madebyfire.com.demologin.viewModels.LoginViewModel;

public class LoginActivity extends BaseActivity implements LoginViewDelegate {
    LoginViewModel loginViewModel;
    private Context mContext;
    private ProgressDialog dialog;
    private Button btnLogin;
    private Button btnRegister;

    private EditText textPhone;
    private EditText textPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = new LoginViewModel(this);
        mContext = this;
        initDialog();
        initView();
        setupListener();
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
}