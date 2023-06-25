package com.e3.test.exception;

public class RequestCanNotCompleteException extends RuntimeException  {

    private static final long serialVersionUID = 7469250123041558214L;
    public RequestCanNotCompleteException(String message) {
        super(message);
    }

    public RequestCanNotCompleteException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestCanNotCompleteException(Throwable cause) {
        super(cause);
    }

    public RequestCanNotCompleteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
