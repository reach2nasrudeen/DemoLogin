package lea.madebyfire.com.demologin.viewModels;

import android.text.TextUtils;

import lea.madebyfire.com.demologin.dataManager.RegisterDataManager;
import lea.madebyfire.com.demologin.interfaces.viewDelegates.RegisterDelegate;
import lea.madebyfire.com.demologin.interfaces.viewResponseDelegates.RegisterViewDelegate;

/**
 * Created by Nasrudeen on 23/05/17.
 */

public class RegisterViewModel extends RegisterBaseViewModel implements RegisterDelegate {

    private RegisterViewDelegate registerViewDelegate;
    private RegisterDataManager registerDataManager;

    public RegisterViewModel(RegisterViewDelegate viewDelegate) {
        registerViewDelegate = viewDelegate;
        registerDataManager = new RegisterDataManager();
    }

    @Override
    public void onRegister() {
        String name = getName();
        String phone = getPhone();
        String password = getPassword();
        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(password)) {
            return;
        }

        registerViewDelegate.onRegisterSuccess();
    }
}
