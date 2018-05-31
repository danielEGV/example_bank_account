package model.account;

public class SavingsAccount extends Account {

    private int safetyDepositBoxID;
    private int safetyDepositBoxKey;

    public SavingsAccount(double balance) {
        super(balance, 1);
    }

    public void loadRate() {

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
}
