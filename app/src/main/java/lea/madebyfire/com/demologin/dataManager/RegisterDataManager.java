package lea.madebyfire.com.demologin.dataManager;

import lea.madebyfire.com.demologin.rest.DemoApiInterface;

import static lea.madebyfire.com.demologin.base.DemoApp.getApp;

/**
 * Created by Nasrudeen on 23/05/17.
 */

public class RegisterDataManager {

    private final String TAG = RegisterDataManager.class.getSimpleName();
    private final DemoApiInterface service;

    public RegisterDataManager() {
        service = getApp().getRetrofitInterface();
    }
}
