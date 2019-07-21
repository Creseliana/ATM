package bank;

public class CardInfo {

    public static final int CARD_FIELDS = 4;
    private String cardNumber;
    private int pin;
    private int balance;
    private boolean isBlocked;
    private long blockDateTime;

    public CardInfo() {

    }

    public CardInfo(String cardNumber, int pin, int balance, boolean isBlocked, long blockDateTime) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.balance = balance;
        this.isBlocked = isBlocked;
        this.blockDateTime = blockDateTime;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public boolean getBlockStatus() {
        return isBlocked;
    }

    public void setBlockStatus(boolean blocked) {
        isBlocked = blocked;
    }

    public long getBlockDateTime() {
        return blockDateTime;
    }

    public void setBlockDateTime(long blockDateTime) {
        this.blockDateTime = blockDateTime;
    }

    public static boolean checkCardFormat(String line) {
        return line.matches("[0-9][0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]");
    }

    @Override
    public String toString() {
        return cardNumber + " " + pin + " " + balance + " " + isBlocked + blockDateTime + "\n";
    }
}
