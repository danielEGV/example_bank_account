package model.bank;

import model.account.Account;
import model.account.CheckingAccount;
import model.account.SavingsAccount;
import model.client.Client;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public interface IBank {
    Client registerClient(String name, int socialSecurity);
    void associateAccountWithClient(Client client, Account account);
    Client registerClient(String name, int socialSecurity, Account account);
    SavingsAccount createSavingsAccount(double balance);
    CheckingAccount createCheckingAccount(double balance);
    void setAccountID(Client client);
    void setCreditOrSafety(Client client);
    void setCreditCard(Account account);
    void setSafetyDepositBox(Account account);
    List<Client> loadClients();
    Account createAccount(String accountType, int initialDeposit);
    void deposit(Client client, double quantity);
    void withdraw(Client client, double quantity);
    void transfer(Client ofClient, Client toClient, double quantity);
    String showInfo(Client client);
}
