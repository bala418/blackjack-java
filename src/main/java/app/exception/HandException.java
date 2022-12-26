package app.exception;

public class HandException extends Exception {
    public HandException(String message) {
        super(message);
    }

    public HandException(String message, Throwable cause) {
        super(message, cause);
    }

    public HandException(Throwable cause) {
        super(cause);
    }

}
