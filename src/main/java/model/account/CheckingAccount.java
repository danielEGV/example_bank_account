package model.account;

public class CheckingAccount extends Account {

    private int debitCardNumber;
    private int debitCardPin;

    public CheckingAccount(double balance) {
        super(balance, 2);
    }

    public void loadRate() {

    }

    public int getDebitCardNumber() {
        return debitCardNumber;
    }

    public void setDebitCardNumber(int debitCardNumber) {
        this.debitCardNumber = debitCardNumber;
    }

    public int getDebitCardPin() {
        return debitCardPin;
    }

    public void setDebitCardPin(int debitCardPin) {
        this.debitCardPin = debitCardPin;
    }
}
