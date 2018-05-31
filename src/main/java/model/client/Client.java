package model.client;

import model.account.Account;

public class Client {
    private String name;
    private int socialSecurity;
    private Account account;

    public Client(String name, int socialSecurity, Account account) {
        this.name = name;
        this.socialSecurity = socialSecurity;
        this.account = account;
    }

    public Client(String name, int socialSecurity) {
        this.name = name;
        this.socialSecurity = socialSecurity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSocialSecurity() {
        return socialSecurity;
    }

    public void setSocialSecurity(int socialSecurity) {
        this.socialSecurity = socialSecurity;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Client[" +
                "name: '" + name + '\'' +
                ", socialSecurity: " + socialSecurity +
                ", account: " + account +
                ']';
    }
}
