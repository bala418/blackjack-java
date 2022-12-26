package app.exception;

public class BetException extends Exception {
    public BetException(String message) {
        super(message);
    }

    public BetException(String message, Throwable cause) {
        super(message, cause);
    }

    public BetException(Throwable cause) {
        super(cause);
    }
}
