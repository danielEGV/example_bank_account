package persistence.user;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import model.account.Account;
import model.client.Client;
import org.davidmoten.rx.jdbc.Database;
import org.davidmoten.rx.jdbc.tuple.Tuple2;
import org.davidmoten.rx.jdbc.tuple.TupleN;
import persistence.connection.ConnectionJDBC;
import persistence.connection.ConnectionRxJDBC;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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
        database.update("INSERT INTO bankdb.clientbank (socialsecurity, nameclient) VALUES (?,?)")
                .parameters(client.getSocialSecurity(), client.getSocialSecurity()).complete();
    }

    @Override
    public void modifyClient(Client client) {

    }

    @Override
    public void deleteClient(Client client) {

    }

    @Override
    public void assignAccount(Client client, Account account) {

    }

    @Override
    public void getClient(int socialSecurity) {
        Flowable<TupleN<Object>> flowable = database
                .select("SELECT * FROM bankdb.clientbank WHERE socialsecurity = ?")
                .parameter(socialSecurity).getTupleN().to(Flowable::sorted);
        List<Object> f = Arrays.asList(flowable);
        System.out.println("**-<>-**");
        flowable.blockingForEach(System.out::println);
    }

    @Override
    public void allClients() {
        database.select("SELECT * FROM bankdb.clientbank")
                .getAs(Integer.class, String.class)
                .forEach(System.out::println);
    }
}
