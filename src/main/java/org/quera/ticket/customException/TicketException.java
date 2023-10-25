package org.quera.ticket.customException;

public class TicketException extends RuntimeException{
    public TicketException() {
    }

    public TicketException(String message) {
        super(message);
    }

    public TicketException(String message, Throwable cause) {
        super(message, cause);
    }

    public TicketException(Throwable cause) {
        super(cause);
    }

    public TicketException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
