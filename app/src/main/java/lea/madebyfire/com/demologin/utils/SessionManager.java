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

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
}
