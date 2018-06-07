package view;

import model.account.Account;
import model.bank.Bank;
import model.bank.IBank;
import model.client.Client;
import org.davidmoten.rx.jdbc.Database;
import persistence.connection.ConnectionJDBC;
import persistence.connection.ConnectionRxJDBC;
import persistence.user.UserPersistence;
import utils.bank_functions.AccountType;
import utils.bank_functions.ActionLog;
import utils.bank_functions.IBankFunctions;

import java.sql.Connection;
import java.util.List;

public class Main {
    public  static List<Client> clients;

    public static void main(String[] args) {
        //System.out.println(IRandomID.randomID.apply(12));
        //System.out.println(ICreateAccountID.lastTwoDigit.apply("Daniel"));

        IBank bank = Bank.getInstance();

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

        clients = bank.loadClients();
        for (Client client : clients) {
            System.out.println("***************************");
            System.out.println(bank.showInfo(client));
        }

        System.out.println("----------------------------------------------------------------------------");
        Client client = IBankFunctions.searchClient.apply(clients, 687057316);
        System.out.println(bank.showInfo(client));

        System.out.println("----------------------------------------------------------------------------");
        new AccountType().separateByTypeOfAccount();

        System.out.println("----------------------------------------------------------------------------");
        new ActionLog().actionLog();

        System.out.println("----------------------------------------------------------------------------");
        //Connection connectionDB = ConnectionJDBC.getConnection();
        //Database database = ConnectionRxJDBC.getConnection();
        /*database.select("SELECT * FROM clientbank")
                .getAs(Integer.class, String.class)
                .blockingForEach(System.out::println);*/

        Client client3 = IBankFunctions.searchClient.apply(clients, 687057316);
        System.out.println(bank.showInfo(client3));

        UserPersistence userPersistence = UserPersistence.getInstance();

        //userPersistence.createClient(client3);

        userPersistence.allClients();
        userPersistence.getClient(12345);

        client3.setName("Julieta Fuentes");

        userPersistence.modifyClient(client3);
        userPersistence.allClients();

        //userPersistence.deleteClient(client3);
        //userPersistence.allClients();

        System.out.println("----------------------------------------------------------------------------");
        ActionLog actionLog = new ActionLog();
        actionLog.actionLog();
        System.out.println(actionLog.getClients());
    }
}
