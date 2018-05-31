package view;

import model.account.Account;
import model.bank.Bank;
import model.bank.IBank;
import model.client.Client;
import utils.create_id.create_account_id.ICreateAccountID;
import utils.create_id.random_id.IRandomID;
import utils.properties.properties_size_ids.properties_size_account_id.IPropetiesSizeAccountID;

public class Main {
    public static void main(String[] args) {
        //System.out.println(IRandomID.randomID.apply(12));
        //System.out.println(ICreateAccountID.lastTwoDigit.apply("Daniel"));

        IBank bank = new Bank();

        Client client1 = bank.registerClient("Daniel Esteban Guevara", 21765);
        Account account1 = bank.createSavingsAccount(20000000);

        bank.associateAccountWithClient(client1, account1);

        System.out.println(bank.showInfo(client1));

        bank.setAccountID(client1);

        System.out.println(bank.showInfo(client1));

        bank.setSafetyDepositBox(client1);

        System.out.println(bank.showInfo(client1));

    }
}
