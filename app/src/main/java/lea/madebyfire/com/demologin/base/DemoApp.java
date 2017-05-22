package lea.madebyfire.com.demologin.base;

import android.app.Application;
import android.content.Context;

import lea.madebyfire.com.demologin.rest.DemoApiClient;
import lea.madebyfire.com.demologin.rest.DemoApiInterface;
import lea.madebyfire.com.demologin.utils.SessionManager;

public class DemoApp extends Application {
    public static Context mContext;
    protected static DemoApp mInstance;
    private SessionManager mSharedPreferences;
    private DemoApiClient demoApiClient;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mContext = getApplicationContext();
        mSharedPreferences = new SessionManager(this);
        demoApiClient = new DemoApiClient();
    }

    public static synchronized Context getContext(){
        return mContext;
    }

    public static DemoApp getApp(){
        if (mInstance != null && mInstance instanceof DemoApp){
            return mInstance;
        }else {
            mInstance = new DemoApp();
            mInstance.onCreate();
            return mInstance;
        }
    }

    public SessionManager getUserPreference() {
        return mSharedPreferences;
    }

    public DemoApiInterface getRetrofitInterface() {
        return demoApiClient.getClientInterface();
    }
}