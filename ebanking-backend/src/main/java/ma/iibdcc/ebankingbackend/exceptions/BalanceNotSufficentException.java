package ma.iibdcc.ebankingbackend.exceptions;

public class BalanceNotSufficentException extends Exception {
    public BalanceNotSufficentException(String msg) {
        super(msg);
    }
}
