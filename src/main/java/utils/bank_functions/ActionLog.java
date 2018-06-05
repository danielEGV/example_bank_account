package utils.bank_functions;

import io.reactivex.Observable;
import model.client.Client;
import view.Main;

public class ActionLog implements IActionLog {

    @Override
    public void actionLog() {
        Observable<Client> observableClient = Observable.fromIterable(Main.clients);

        System.out.println(">>>>>>> Mayor a menor <<<<<<<");
        observableClient
                .sorted((c1, c2) -> (int) (c2.getAccount().getBalance() - c1.getAccount().getBalance()))
                .forEach(c -> creationLog(c.getName(), c.getAccount().getBalance()));
    }

    @Override
    public void creationLog(String name, double balance) {
        System.out.println(">> " + name + " - $" + balance);
    }
}
