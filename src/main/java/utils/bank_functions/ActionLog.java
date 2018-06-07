package utils.bank_functions;

import io.reactivex.Observable;
import model.client.Client;
import view.Main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ActionLog implements IActionLog {

    @Override
    public void actionLog() {
        Observable<Client> observableClient = Observable.fromIterable(Main.clients);

        System.out.println(">>>>>>> Mayor a menor <<<<<<<");
        observableClient
                .sorted((c1, c2) -> (int) (c2.getAccount().getBalance() - c1.getAccount().getBalance()))
                .take(5)
                .map(c -> Arrays.asList(c.getName(), c.getAccount().getBalance()))
                .forEach(this::creationLog);
    }


    @Override
    public void creationLog(List list) {
        list.stream()
                .reduce((x, y) -> x + " - $" + y)
                .ifPresent(System.out::println);
    }
}
