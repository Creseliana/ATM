package client;

public abstract class ClientAccount {

    private String userCardNumber;
    private int userPin;

    public ClientAccount() {

    }

    public ClientAccount(String userCardNumber, int userPin) {
        this.userCardNumber = userCardNumber;
        this.userPin = userPin;
    }

    public String getUserCardNumber() {
        return userCardNumber;
    }

    public void setUserCardNumber(String userCardNumber) {
        this.userCardNumber = userCardNumber;
    }

    public int getUserPin() {
        return userPin;
    }

    public void setUserPin(int userPin) {
        this.userPin = userPin;
    }

}
