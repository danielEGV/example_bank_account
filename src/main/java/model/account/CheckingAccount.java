package model.account;

public class CheckingAccount extends Account {

    private int debitCardNumber;
    private int debitCardPin;

    public CheckingAccount(double balance) {
        super(balance, 2);
    }

    public CheckingAccount() {
        super(2);
    }

    public void loadRate() {

    }

    @Override
    public void setIdentify(int identify) {
        this.debitCardNumber = identify;
    }

    @Override
    public void setKey(int key) {
        this.debitCardPin = key;
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

    @Override
    public String toString() {
        return super.toString() + "\nCheckingAccount[" +
                "debitCardNumber: " + debitCardNumber +
                ", debitCardPin: " + debitCardPin +
                ']';
    }
}
