package model.account;

import model.rate.BaseRate;

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
        this.rate = new BaseRate().baseRate() * 0.15;
    }

    @Override
    public void setIdentify(int identify) {
        this.debitCardNumber = identify;
        setModificationDate();
    }

    @Override
    public void setKey(int key) {
        this.debitCardPin = key;
        setModificationDate();
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
