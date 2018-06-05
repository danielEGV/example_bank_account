import model.account.Account;
import model.bank.Bank;
import model.client.Client;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

@RunWith(value = Parameterized.class)
public class DepositSavingsAccountTest {
    Bank bank;
    Client client;
    Account account;

    double depositValue;
    double expected;

    public DepositSavingsAccountTest(double depositValue, double expected) {
        this.depositValue = depositValue;
        this.expected = expected;
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
        account = bank.createSavingsAccount(8000);
        bank.associateAccountWithClient(client, account);
    }

    @Test
    public void depoitTest() {
        bank.deposit(client, depositValue);
        double result = client.getAccount().getBalance();
        Assert.assertEquals(result, expected, 0.02);
    }
}
