package lea.madebyfire.com.demologin.interfaces.viewResponseDelegates;


public interface StateViewDelegate {
    void showErrorMessage(int errorMessage, MessageViewType viewType);
    void showErrorMessage(String errorMessage, MessageViewType viewType);
    void showSuccessMessage(int message, MessageViewType viewType);
}
