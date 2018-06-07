package utils.bank_functions;

import io.reactivex.Observable;
import model.client.Client;
import view.Main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ActionLog implements IActionLog {
    private List<Client> clients;

    public ActionLog() {
        clients = new ArrayList<>();
    }

    @Override
    public void actionLog() {
        Observable<Client> observableClient = Observable.fromIterable(Main.clients);
        Observable<Client> testObsevableClient = Observable.fromIterable(Main.clients);

        System.out.println(">>>>>>> Mayor a menor <<<<<<<");
        observableClient
                .sorted((c1, c2) -> (int) (c2.getAccount().getBalance() - c1.getAccount().getBalance()))
                .take(5)
                .map(c -> Arrays.asList(c.getName(), c.getAccount().getBalance()))
                .forEach(this::creationLog);

        testObsevableClient
                .sorted((client1, client2) -> (int)(client2.getAccount().getBalance() - client1.getAccount().getBalance()))
                .take(5)
                .subscribe(client -> clients.add(client));
    }

    public List<Client> getClients() {
        return clients;
    }


    @Override
    public void creationLog(List list) {
        list.stream()
                .reduce((x, y) -> x + " - $" + y)
                .ifPresent(System.out::println);
    }
}
