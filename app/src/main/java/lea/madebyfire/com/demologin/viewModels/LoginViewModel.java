package lea.madebyfire.com.demologin.viewModels;

import android.text.TextUtils;

import lea.madebyfire.com.demologin.dataManager.LoginDataManager;
import lea.madebyfire.com.demologin.dataManager.callbacks.DataResponse;
import lea.madebyfire.com.demologin.interfaces.viewDelegates.LoginDelegate;
import lea.madebyfire.com.demologin.interfaces.viewResponseDelegates.LoginViewDelegate;
import lea.madebyfire.com.demologin.interfaces.viewResponseDelegates.MessageViewType;

/**
 * Created by Nasrudeen on 22/05/17.
 */

public class LoginViewModel extends LoginBaseViewModel implements LoginDelegate {
    private LoginViewDelegate loginViewDelegate;
    private LoginDataManager loginDataManager;

    public LoginViewModel(LoginViewDelegate viewDelegate) {
        loginViewDelegate = viewDelegate;
        loginDataManager = new LoginDataManager();
    }

    @Override
    public void onLogin() {
        String phoneNo = getPhoneNumber();
        String password = getPassword();

        if (TextUtils.isEmpty(phoneNo)) {
            return;
        }
        if (TextUtils.isEmpty(password)) {
            return;
        }
        loginViewDelegate.launchHomeScreen();
        /*loginViewDelegate.showProgressView(true);
        loginDataManager.doLogin(phoneNo, password, new DataResponse<String>() {
            @Override
            public void onSuccess(String message) {
                //No implementation
            }

            @Override
            public void onSuccess(String item, String message) {
                loginViewDelegate.showProgressView(false);
                loginViewDelegate.launchHomeScreen();
            }

            @Override
            public void onFailure(String errorMessage) {
                loginViewDelegate.showProgressView(false);
                loginViewDelegate.showErrorMessage(errorMessage, MessageViewType.Toast);
            }

            @Override
            public void onFailure(String errorMessage, String statusCode) {
                loginViewDelegate.showProgressView(false);
                loginViewDelegate.showErrorMessage(errorMessage, MessageViewType.Toast);
            }
        });*/

    }

    @Override
    public void onRegister() {
        loginViewDelegate.launchRegister();
    }
}
