package lea.madebyfire.com.demologin.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import lea.madebyfire.com.demologin.R;
import lea.madebyfire.com.demologin.activity.LoginActivity;
import lea.madebyfire.com.demologin.utils.SessionManager;

public class SplashActivity extends BaseActivity {
    private SessionManager mSessionManager;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mContext = this;
        mSessionManager = new SessionManager(mContext);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                initActivity(new Intent(mContext, LoginActivity.class));

            }
        }, 3 * 1000); // wait for 3 seconds
    }

    private void initActivity(Intent intent) {
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        finish();
    }
}

