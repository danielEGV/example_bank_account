package model.account;

public class SavingsAccount extends Account {

    private int safetyDepositBoxID;
    private int safetyDepositBoxKey;

    public SavingsAccount(double balance) {
        super(balance, 1);
    }

    public SavingsAccount() {
        super(1);
    }

    public void loadRate() {

    }

    @Override
    public void setIdentify(int identify) {
        this.safetyDepositBoxID = identify;
    }

    @Override
    public void setKey(int key) {
        this.safetyDepositBoxKey = key;
    }

    public int getSafetyDepositBoxID() {
        return safetyDepositBoxID;
    }

    public void setSafetyDepositBoxID(int safetyDepositBoxID) {
        this.safetyDepositBoxID = safetyDepositBoxID;
    }

    public int getSafetyDepositBoxKey() {
        return safetyDepositBoxKey;
    }

    public void setSafetyDepositBoxKey(int safetyDepositBoxKey) {
        this.safetyDepositBoxKey = safetyDepositBoxKey;
    }

    @Override
    public String toString() {
        return super.toString() + "SavingsAccount[" +
                "safetyDepositBoxID: " + safetyDepositBoxID +
                ", safetyDepositBoxKey: " + safetyDepositBoxKey +
                ']';
    }
}
