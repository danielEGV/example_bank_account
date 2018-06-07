package persistence.user;

import model.account.Account;
import model.client.Client;

import java.util.function.Function;

public interface IUserPersisntence<T> {
    void createClient(Client client);
    void modifyClient(Client client);
    void deleteClient(Client client);
    void assignAccount(Client client, Account account);
    void getClient(int socialSecurity);
    void allClients();
}
