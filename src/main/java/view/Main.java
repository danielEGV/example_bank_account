package view;

import model.account.Account;
import model.bank.Bank;
import model.bank.IBank;
import model.client.Client;
import utils.create_id.create_account_id.ICreateAccountID;
import utils.create_id.random_id.IRandomID;
import utils.properties.properties_size_ids.properties_size_account_id.IPropetiesSizeAccountID;
import utils.read_csv.ReadCSV;
import utils.read_file.ReadFileBufferedReader;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //System.out.println(IRandomID.randomID.apply(12));
        //System.out.println(ICreateAccountID.lastTwoDigit.apply("Daniel"));

        IBank bank = new Bank();

        Client client1 = bank.registerClient("Daniel Esteban Guevara", 21765);
        Account account1 = bank.createSavingsAccount(20000000);

        bank.associateAccountWithClient(client1, account1);
        //System.out.println(bank.showInfo(client1));

        bank.setAccountID(client1);
        //System.out.println(bank.showInfo(client1));

        bank.setCreditOrSafety(client1);
        System.out.println(bank.showInfo(client1));

        System.out.println("-------");

        Client client2 = bank.registerClient("Camila Hernandez Ortiz", 12442);
        Account account2 = bank.createCheckingAccount(5000000);

        bank.associateAccountWithClient(client2, account2);
        //System.out.println(bank.showInfo(client2));

        bank.setAccountID(client2);
        //System.out.println(bank.showInfo(client2));

        //bank.setCreditCard(client2);
        bank.setCreditOrSafety(client2);
        System.out.println(bank.showInfo(client2));

        bank.deposit(client1, 3000000);
        System.out.println(bank.showInfo(client1));

        bank.withdraw(client1, 10000000);
        System.out.println(bank.showInfo(client1));

        bank.transfer(client1, client2, 3000000);
        System.out.println(bank.showInfo(client1));
        System.out.println(bank.showInfo(client2));

        System.out.println("-------");
        /*
        ReadCSV readCSV = new ReadCSV(new ReadFileBufferedReader("files_csv/NewBankAccount.csv"));
        try {
            for (String[] v : readCSV.makeListClients()) {
                for (String c : v) {
                    System.out.println(c);
                }
            }
        } catch (IOException e) {
            System.out.println("File error.");
        }*/

        List<Client> clients = bank.loadClients();
        for (Client client : clients) {
            System.out.println("***************************");
            System.out.println(bank.showInfo(client));
        }
    }
}
