import model.bank.Bank;
import model.client.Client;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

@RunWith(value = Parameterized.class)
public class WithdrawSavingsAccountTest {

    Bank bank;
    Client client;
    static final double balanceInit = 8000;

    double withdrawValue;
    double expected;

    public WithdrawSavingsAccountTest(double withdrawValue, double expected) {
        this.withdrawValue = withdrawValue;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Iterable<Object[]> getData() {
        return Arrays.asList(new Object[][]{
                {8000, balanceInit - 8000}, {2000, balanceInit - 2000}, {5000, balanceInit - 5000}
        });
    }

    @Before
    public void before() {
        bank = Bank.getInstance();
        client = bank.registerClient("Daniel", 12345, bank.createCheckingAccount(balanceInit));
    }

    @Test
    public void withdrawTest() {
        bank.withdraw(client, withdrawValue);
        double result = client.getAccount().getBalance();
        Assert.assertEquals(expected, result, 0.02);
    }
}
