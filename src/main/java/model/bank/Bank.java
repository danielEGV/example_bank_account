package model.bank;

import model.account.Account;
import model.account.CheckingAccount;
import model.account.SavingsAccount;
import model.client.Client;
import utils.bank_functions.IBankFunctions;
import utils.create_id.create_account_id.CreateAccountID;
import utils.create_id.create_checking_id.CreateChecking;
import utils.create_id.create_checking_id.ICreateChecking;
import utils.create_id.create_savings_id.CreateSavings;
import utils.create_id.create_savings_id.ICreateSavings;
import utils.exceptions.NegativeBalanceException;
import utils.properties.properties_size_ids.properties_size_account_id.PropertiesSizeAccountID;
import utils.properties.properties_size_ids.properties_size_checking_id.PropertiesSizeCheckingID;
import utils.properties.properties_size_ids.properties_size_savings_id.PropertiesSizeSavingID;
import utils.read_csv.IReadCSV;
import utils.read_csv.ReadCSV;
import utils.read_file.IReadFileBufferedReader;
import utils.read_file.ReadFileBufferedReader;
import utils.read_file.ReadFileInputStream;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bank implements IBank {

    private int index;
    private static Bank bank;

    private Bank() {
        index = 10000;
    }

    public static Bank getInstance() {
        if (bank == null) {
            bank = new Bank();
        }
        return bank;
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

    @Override
    public void setCreditOrSafety(Client client) {
        Account account = client.getAccount();
        if (account instanceof CheckingAccount) {
            this.setCreditCard(account);
        } else if (account instanceof SavingsAccount) {
            this.setSafetyDepositBox(account);
        }
    }

    public void setCreditCard(Account account) {
        ICreateChecking createChecking = new CreateChecking(
                new PropertiesSizeCheckingID(
                        new ReadFileInputStream("properties_bank/size_id_account.properties")
                )
        );
        account.setIdentify(createChecking.createCreditCardNumber());
        account.setKey(createChecking.createCreditCardPin());
    }

    public void setSafetyDepositBox(Account account) {
        ICreateSavings createSavings = new CreateSavings(
                new PropertiesSizeSavingID(
                        new ReadFileInputStream("properties_bank/size_id_account.properties")
                )
        );
        account.setIdentify(createSavings.createSafetyDepositBoxID());
        account.setKey(createSavings.createSafetyDepositBoxKey());
    }

    public List<Client> loadClients() {
        List<Client> clients = new ArrayList<>();
        String nameClient, accountType;
        int socialSecurityClient, initialDeposit;
        Account account;
        Client client;

        IReadCSV readCSV = new ReadCSV(new ReadFileBufferedReader("files_csv/NewBankAccount.csv"));
        try {
            for (String[] data: readCSV.makeListClients())
            {
                nameClient = IBankFunctions.nameClient.apply(data);
                socialSecurityClient = IBankFunctions.socialSecurityClient.apply(data);
                accountType = IBankFunctions.accountType.apply(data);
                initialDeposit = IBankFunctions.initialDesposit.apply(data);
                client = registerClient(nameClient, socialSecurityClient);
                account = createAccount(accountType, initialDeposit);
                associateAccountWithClient(client, account);
                setAccountID(client);
                setCreditOrSafety(client);
                clients.add(client);
            }
        } catch (IOException e) {
            System.out.println("File error.");
        }
        return clients;
    }

    public Account createAccount(String accountType, int initialDeposit) {
        if (IBankFunctions.isSavings.apply(accountType)) {
            return createSavingsAccount(initialDeposit);
        } else if (IBankFunctions.isChecking.apply(accountType)) {
            return createCheckingAccount(initialDeposit);
        }
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
