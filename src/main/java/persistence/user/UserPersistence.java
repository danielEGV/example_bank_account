package persistence.user;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import model.account.Account;
import model.client.Client;
import model.client.IClient;
import org.davidmoten.rx.jdbc.Database;
import org.davidmoten.rx.jdbc.tuple.Tuple2;
import org.davidmoten.rx.jdbc.tuple.TupleN;
import persistence.connection.ConnectionJDBC;
import persistence.connection.ConnectionRxJDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UserPersistence implements IUserPersisntence {
    private Flowable<Integer> insert;
    private static UserPersistence userPersistence;
    private Database database;

    private UserPersistence() {
        database = ConnectionRxJDBC.getConnection();
    }

    public static UserPersistence getInstance() {
        if (userPersistence == null) {
            userPersistence = new UserPersistence();
        }
        return userPersistence;
    }

    @Override
    public void createClient(Client client) {
        try {
            int c = database.update("INSERT INTO bankdb.clientbank (socialsecurity, nameclient) VALUES (?,?)")
                    .parameters(client.getSocialSecurity(), client.getName())
                    .counts()
                    .toList()
                    .blockingGet()
                    .get(0);
            System.out.println("x -> " + c);
        } catch (RuntimeException e) {
            e.printStackTrace();
            System.out.println("Duplicate.");
        }
    }

    @Override
    public void modifyClient(Client client) {
        int c = database.update("UPDATE bankdb.clientbank SET nameclient = ? WHERE socialsecurity = ?")
                .parameters(client.getName(), client.getSocialSecurity())
                .counts()
                .toList()
                .blockingGet()
                .get(0);
        System.out.println("y -> " + c);
    }

    @Override
    public void deleteClient(Client client) {
        int c = database.update("DELETE FROM bankdb.clientbank WHERE socialsecurity = ?")
                .parameter(client.getSocialSecurity())
                .counts()
                .toList()
                .blockingGet()
                .get(0);
    }

    @Override
    public void assignAccount(Client client, Account account) {

    }

    @Override
    public void getClient(int socialSecurity) {
        List<IClient> iClients = database
                .select("SELECT * FROM bankdb.clientbank WHERE socialsecurity = ?")
                .parameter(socialSecurity)
                .autoMap(IClient.class)
                .toList()
                .blockingGet();

        for (IClient iClient: iClients) {
            Client client = new Client(iClient.getName(), iClient.getSocialSecurity());
            System.out.println(client.toString());
        }

        System.out.println();

        //List arrayList = clientFlowable.as(Arrays::asList);
        //System.out.println(arrayList.get(0));
    }

    @Override
    public void allClients() {
        List<IClient> iClients = database.select("SELECT * FROM bankdb.clientbank")
                .autoMap(IClient.class)
                .toList()
                .blockingGet();

        for (IClient iClient: iClients) {
            Client client = new Client(iClient.getName(), iClient.getSocialSecurity());
            System.out.println(client.toString());
        }
    }
}
