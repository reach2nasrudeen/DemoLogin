package lea.madebyfire.com.demologin.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import lea.madebyfire.com.demologin.R;
import lea.madebyfire.com.demologin.base.BaseActivity;
import lea.madebyfire.com.demologin.interfaces.viewDelegates.RegisterDelegate;
import lea.madebyfire.com.demologin.interfaces.viewResponseDelegates.MessageViewType;
import lea.madebyfire.com.demologin.interfaces.viewResponseDelegates.RegisterViewDelegate;
import lea.madebyfire.com.demologin.utils.SessionManager;
import lea.madebyfire.com.demologin.viewModels.RegisterViewModel;

public class RegisterActivity extends BaseActivity implements RegisterViewDelegate {
    Button btnRegister;
    EditText textName;
    EditText textPhone;
    EditText textPassword;
    RegisterViewModel registerViewModel;
    Toolbar toolbar;
    SessionManager sessionManager;
    Context mContext;
    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext  = this;
        setContentView(R.layout.activity_register);
        registerViewModel = new RegisterViewModel(this);
        sessionManager = getApp().getUserPreference();
        initView();
        initToolbar();
        setupListener();
        initDialog();
    }

    private void initDialog() {
        dialog = new ProgressDialog(mContext);
        dialog.setProgressStyle(android.R.attr.progressBarStyleSmall);
        dialog.setMessage(getString(R.string.loading));
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
    }
    private void setupListener() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerViewModel.setName(textName.getText().toString());
                registerViewModel.setPhone(textPhone.getText().toString());
                registerViewModel.setPassword(textPassword.getText().toString());
                registerViewModel.onRegister();
            }
        });
    }

    private void initView() {
        btnRegister = (Button) findViewById(R.id.btnRegister);
        textName = (EditText) findViewById(R.id.textName);
        textPhone = (EditText) findViewById(R.id.textPhone);
        textPassword = (EditText) findViewById(R.id.textPassword);
    }

    public void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == android.R.id.home) {
            goHome();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        goHome();
    }

    private void goHome() {
        Intent intent = new Intent(this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public void onRegisterSuccess() {
        sessionManager.setUserLoginStatus(true);
        sessionManager.setUsername(registerViewModel.getName());
        sessionManager.setUserPhone(registerViewModel.getPhone());
        initActivity(new Intent(this, HomeActivity.class));
    }

    private void initActivity(Intent intent) {
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        finish();
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
}
