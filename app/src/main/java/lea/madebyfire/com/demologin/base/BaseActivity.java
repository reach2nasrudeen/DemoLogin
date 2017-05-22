package lea.madebyfire.com.demologin.base;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import lea.madebyfire.com.demologin.R;

public class BaseActivity extends AppCompatActivity {
    private boolean isDoubbleTappedToExit;

    public void onBackPressed(View view) {
        if (isDoubbleTappedToExit) {
            super.onBackPressed();
            return;
        }
        this.isDoubbleTappedToExit = true;
        Snackbar.make(view, getResources().getString(R.string.press_again_to_exit), Snackbar.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isDoubbleTappedToExit = false;
            }
        }, 5000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public DemoApp getApp(){
        return (DemoApp) getApplication();
    }
}
