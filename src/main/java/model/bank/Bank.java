package model.bank;

import model.account.Account;
import model.account.CheckingAccount;
import model.account.SavingsAccount;
import model.client.Client;
import utils.create_id.create_account_id.CreateAccountID;
import utils.create_id.create_checking_id.CreateChecking;
import utils.create_id.create_checking_id.ICreateChecking;
import utils.create_id.create_savings_id.CreateSavings;
import utils.create_id.create_savings_id.ICreateSavings;
import utils.exceptions.NegativeBalanceException;
import utils.properties.properties_size_ids.properties_size_account_id.PropertiesSizeAccountID;
import utils.properties.properties_size_ids.properties_size_checking_id.PropertiesSizeCheckingID;
import utils.properties.properties_size_ids.properties_size_savings_id.PropertiesSizeSavingID;
import utils.read_file.ReadFileInputStream;

import java.util.List;

public class Bank implements IBank {

    private int index;

    public Bank() {
        index = 10000;
    }

    public Client registerClient(String name, int socialSecurity) {
        return new Client(name, socialSecurity);
    }

    @Override
    public void associateAccountWithClient(Client client, Account account) {
        client.setAccount(account);
    }

    public Client registerClient(String name, int socialSecurity, Account account) {
        return new Client(name, socialSecurity, account);
    }

    public SavingsAccount createSavingsAccount(double balance) {
        index++;
        return new SavingsAccount(balance);
    }

    public CheckingAccount createCheckingAccount(double balance) {
        index++;
        return new CheckingAccount(balance);
    }

    public void setAccountID(Client client) {
        int type = 0;
        if (client.getAccount() instanceof SavingsAccount) {
            type = 1;
        } else if (client.getAccount() instanceof CheckingAccount) {
            type = 2;
        }
        client.getAccount().setAccountID(
                new CreateAccountID(
                        new PropertiesSizeAccountID(
                                new ReadFileInputStream("properties_bank/size_id_account.properties")
                        )
                ).getAccountID(type, client.getSocialSecurity(), index)
        );
    }

    public void setCreditCard(Client client) {
        ICreateChecking createChecking = new CreateChecking(
                new PropertiesSizeCheckingID(
                        new ReadFileInputStream("properties_bank/size_id_account.properties")
                )
        );
        client.getAccount().setIdentify(createChecking.createCreditCardNumber());
        client.getAccount().setKey(createChecking.createCreditCardPin());
    }

    public void setSafetyDepositBox(Client client) {
        ICreateSavings createSavings = new CreateSavings(
                new PropertiesSizeSavingID(
                        new ReadFileInputStream("properties_bank/size_id_account.properties")
                )
        );
        client.getAccount().setIdentify(createSavings.createSafetyDepositBoxID());
        client.getAccount().setKey(createSavings.createSafetyDepositBoxKey());
    }

    public List<Client> loadClients() {
        return null;
    }

    public void deposit(Client client, double quantity) {
        client.getAccount().incrementBalance(quantity);
    }

    public void withdraw(Client client, double quantity) {
        try {
            if (quantity > client.getAccount().getBalance()) {
                throw new NegativeBalanceException();
            }
            client.getAccount().decrementBalance(quantity);
        } catch (NegativeBalanceException e) {
            e.getMessageError();
        }
    }

    public void transfer(Client ofClient, Client toClient, double quantity) {
        try {
            if (quantity > ofClient.getAccount().getBalance()) {
                throw new NegativeBalanceException();
            }
            ofClient.getAccount().decrementBalance(quantity);
            toClient.getAccount().incrementBalance(quantity);
        } catch (NegativeBalanceException e) {
            e.getMessageError();
        }
    }

    public String showInfo(Client client) {
        return client.toString();
    }
}
