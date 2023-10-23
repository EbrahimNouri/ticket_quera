package org.quera.ticket.customException;

public class AddMatchException extends  RuntimeException{
    public AddMatchException() {
    }

    public AddMatchException(String message) {
        super(message);
    }

    public AddMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddMatchException(Throwable cause) {
        super(cause);
    }

    public AddMatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
