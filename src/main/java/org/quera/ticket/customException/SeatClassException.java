package org.quera.ticket.customException;

public class SeatClassException extends RuntimeException {
    public SeatClassException() {
    }

    public SeatClassException(String message) {
        super(message);
    }

    public SeatClassException(String message, Throwable cause) {
        super(message, cause);
    }

    public SeatClassException(Throwable cause) {
        super(cause);
    }

    public SeatClassException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
