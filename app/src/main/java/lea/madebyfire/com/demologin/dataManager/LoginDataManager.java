package lea.madebyfire.com.demologin.dataManager;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import lea.madebyfire.com.demologin.dataManager.callbacks.DataResponse;
import lea.madebyfire.com.demologin.rest.DemoApiInterface;
import lea.madebyfire.com.demologin.utils.AppConstants;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static lea.madebyfire.com.demologin.base.DemoApp.getApp;

/**
 * Created by Nasrudeen on 22/05/17.
 */

public class LoginDataManager {
    private final String TAG = LoginDataManager.class.getSimpleName();
    private final DemoApiInterface service;

    public LoginDataManager() {

        service = getApp().getRetrofitInterface();
    }

    public void doLogin(String phone, String password, final DataResponse<String[]> dataResponse) {
        Call<ResponseBody> loginServiceCall = service.loginUser(phone, password);
        loginServiceCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                int statusCode = response.code();

                if (statusCode == 200) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());

                        Log.e("Status", jsonObject.toString());
                        Log.e("Status", "Login Success");
                        dataResponse.onSuccess(new String[]{jsonObject.toString()}, "Login Success");
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    dataResponse.onFailure(AppConstants.ERROR_STATUS, String.valueOf(statusCode));
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "onResponse: " + t.getMessage());
                dataResponse.onFailure("Something went wrong while trying login!");
            }
        });
    }

}
