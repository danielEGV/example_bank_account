package utils.exceptions;

public class NegativeBalanceException extends Exception {

    public NegativeBalanceException(String message) {
        super(message);
    }

    public NegativeBalanceException() {}

    public String getMessageError() {
        return "It's not possible to do the action. Negative balance";
    }
}
