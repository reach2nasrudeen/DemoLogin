package lea.madebyfire.com.demologin.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import lea.madebyfire.com.demologin.R;
import lea.madebyfire.com.demologin.base.BaseActivity;
import lea.madebyfire.com.demologin.utils.SessionManager;

public class HomeActivity extends BaseActivity {
    private TextView textStatus;
    private Button btnLogout;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sessionManager = getApp().getUserPreference();
        initView();
        initToolbar();
        setupListener();
        setupDefaults();
    }

    private void setupDefaults() {
        textStatus.setText("Username : "+sessionManager.getUsername());
        textStatus.append("\nPhone : "+sessionManager.getUserPhone());
    }

    private void initView() {
        textStatus = (TextView) findViewById(R.id.textStatus);
        btnLogout = (Button) findViewById(R.id.btnLogout);
    }

    public void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
    }

    private void setupListener() {
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionManager.setUserLoginStatus(false);
                initActivity(new Intent(HomeActivity.this, LoginActivity.class));
            }
        });
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
