package lea.madebyfire.com.demologin.base;


public class WebServiceURL {

    public static String BASE_URL;

    public static String SERVICE_PATH;

    public WebServiceURL(){

        BASE_URL = "http://192.168.1.24";
        SERVICE_PATH = "/DemoApi/";
    }

    // Endpoints

    public static final String USER_REGISTER = "createUser";
}