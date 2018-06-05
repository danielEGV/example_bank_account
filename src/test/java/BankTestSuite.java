import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(value = Suite.class)
@Suite.SuiteClasses({
        DepositCheckingAccountTest.class,
        DepositSavingsAccountTest.class,
        WithdrawCheckingAccountTest.class,
        WithdrawSavingsAccountTest.class
})
public class BankTestSuite { }