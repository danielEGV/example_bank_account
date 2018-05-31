package model.account;

public abstract class Account {
    protected int accountID;
    protected double balance;
    protected double rate;
    protected int type;

    public Account(double balance, int type) {
        this.balance = balance;
        this.type = type;
    }

    public abstract void loadRate();

    public void incrementBalance(double quantity) {
        this.balance += quantity;
    }

    public void decrementBalance(double quantity) {
        this.balance -= quantity;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Account[" +
                "accountID: " + accountID +
                ", balance: " + balance +
                ", rate: " + rate +
                ']';
    }
}