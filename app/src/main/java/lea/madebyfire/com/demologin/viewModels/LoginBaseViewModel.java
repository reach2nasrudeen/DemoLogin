package lea.madebyfire.com.demologin.viewModels;

/**
 * Created by Nasrudeen on 22/05/17.
 */

public class LoginBaseViewModel {
    private String phoneNumber;
    private String password;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
