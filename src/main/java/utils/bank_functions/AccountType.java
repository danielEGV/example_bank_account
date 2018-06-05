package utils.bank_functions;

import io.reactivex.Observable;
import model.client.Client;
import view.Main;

public class AccountType implements IAccountType {
    @Override
    public void separateByTypeOfAccount() {
         Observable<Client> observableSavingsAccounts = Observable.fromIterable(Main.clients);
         Observable<Client> observableCheckingAccounts = Observable.fromIterable(Main.clients);

         System.out.println("Savings account: ");
         observableSavingsAccounts
                 .filter(t -> t.getAccount().getType() == 1)
                 .forEach(System.out::println);

         System.out.println("Checking account: ");
         observableCheckingAccounts
                 .filter(t -> t.getAccount().getType() == 2)
                 .forEach(System.out::print);
    }
}
