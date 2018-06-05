import model.account.Account;
import model.account.CheckingAccount;
import model.bank.Bank;
import model.bank.IBank;
import model.client.Client;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Iterator;

@RunWith(value = Parameterized.class)
public class DepositCheckingAccountTest {

    Bank bank;
    Client client;
    Account account;

    double depositValue;
    double depositExpected;

    public DepositCheckingAccountTest(double depositValue, double depositExpected) {
        this.depositValue = depositValue;
        this.depositExpected = depositExpected;
    }

    @Parameterized.Parameters
    public static Iterable<Object[]> getData() {
        return Arrays.asList(new Object[][]{
                {2000, 10000}, {15000, 23000}, {0, 8000}
        });
    }

    @Before
    public void before() {
        bank = Bank.getInstance();
        client = bank.registerClient("Daniel", 12345);
        account = bank.createCheckingAccount(8000);
        bank.associateAccountWithClient(client, account);
    }

    @Test
    public void testClient() {
        bank.deposit(client, depositValue);
        double result = client.getAccount().getBalance();
        Assert.assertEquals(depositExpected, result, 0.2);
    }

}
