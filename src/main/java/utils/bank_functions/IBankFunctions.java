package utils.bank_functions;

import java.util.function.Function;

public interface IBankFunctions {
    String SAVINGS = "Savings";
    String CHECKING = "Checking";
    Function<String, Boolean> isSavings = x -> x.equals(SAVINGS);
    Function<String, Boolean> isChecking = x -> x.equals(CHECKING);
    Function<String[], String> nameClient = x -> x[0];
    Function<String[], Integer> socialSecurityClient = x -> Integer.parseInt(x[1]);
    Function<String[], String> accountType = x -> x[2];
    Function<String[], Integer> initialDesposit = x -> Integer.parseInt(x[3]);
}
