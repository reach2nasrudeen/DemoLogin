package lea.madebyfire.com.demologin.rest;

import java.util.concurrent.TimeUnit;

import lea.madebyfire.com.demologin.base.WebServiceURL;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class DemoApiClient {

    private static Retrofit retrofit = null;
    private static WebServiceURL webServiceURL;

    public DemoApiClient() {
    }

    public DemoApiInterface getClientInterface() {
        webServiceURL = new WebServiceURL();
        if (retrofit == null) {
            OkHttpClient defaultHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)) // Print details of every request through retrofit.
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(webServiceURL.BASE_URL + webServiceURL.SERVICE_PATH)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .client(defaultHttpClient)
                    .build();
        }
        return retrofit.create(DemoApiInterface.class);
    }

}
