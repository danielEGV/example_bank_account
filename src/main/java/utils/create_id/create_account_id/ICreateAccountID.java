package utils.create_id.create_account_id;

import java.util.function.Function;

public interface ICreateAccountID {
    int getAccountID(int type, int socialSecurity, int index);
    String randomID();
    Function<Integer, String> getString = Object::toString;
    Function<String, Integer> getInteger = Integer::parseInt;
    Function<String, String> lastTwoDigit = x -> x.substring(x.length() - 2);
}
