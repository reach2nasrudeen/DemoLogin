package lea.madebyfire.com.demologin.rest;


import lea.madebyfire.com.demologin.base.WebServiceURL;
import lea.madebyfire.com.demologin.utils.AppConstants;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface DemoApiInterface {

    // POST
    @FormUrlEncoded
    @POST(WebServiceURL.USER_REGISTER)
    Call<ResponseBody> registerUser(@Field(AppConstants.PARAM_USERNAME) String name,
                                    @Field(AppConstants.PARAM_USER_PHONE) String phone,
                                    @Field(AppConstants.PARAM_PASSWORD) String password);

    @FormUrlEncoded
    @POST(WebServiceURL.LOGIN_REGISTER)
    Call<ResponseBody> loginUser(@Field(AppConstants.PARAM_USER_PHONE) String phone,
                                    @Field(AppConstants.PARAM_PASSWORD) String password);
}
