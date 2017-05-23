package lea.madebyfire.com.demologin.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Nasrudeen on 22/05/17.
 */

public class SessionManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    private int PRIVATE_MODE = 0;
    private static final String PREFER_NAME = "demoPreferences";
    private static final String KEY_USER_LOGIN = "USER_LOGGED_IN";
    private static final String KEY_USER_NAME = "USER_NAME";
    private static final String KEY_USER_PHONE = "USER_PHONE";

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setUserLoginStatus(boolean isLoggedIn) {
        editor.putBoolean(KEY_USER_LOGIN, isLoggedIn);
        editor.commit();
    }

    public boolean getUserLoginStatus() {
        return pref.getBoolean(KEY_USER_LOGIN, false);
    }
    public void setUsername(String username) {
        editor.putString(KEY_USER_NAME, username);
        editor.commit();
    }

    public String getUsername() {
        return pref.getString(KEY_USER_NAME, "");
    }
    public void setUserPhone(String userPhone) {
        editor.putString(KEY_USER_PHONE, userPhone);
        editor.commit();
    }

    public String getUserPhone() {
        return pref.getString(KEY_USER_PHONE, "");
    }
}
