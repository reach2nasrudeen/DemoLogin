package lea.madebyfire.com.demologin.base;


public class WebServiceURL {

    public static String BASE_URL;

    public static String SERVICE_PATH;

    public WebServiceURL(){

        BASE_URL = "http://ragubathi.tk";
        SERVICE_PATH = "/nas/v1/";
    }

    // Endpoints

    public static final String USER_REGISTER = "createUser";
    public static final String LOGIN_REGISTER = "loginUser";
}