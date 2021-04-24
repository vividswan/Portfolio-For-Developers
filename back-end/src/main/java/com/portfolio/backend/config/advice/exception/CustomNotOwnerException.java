package com.portfolio.backend.config.advice.exception;

public class CustomNotOwnerException extends RuntimeException{

    public CustomNotOwnerException() {
        super();
    }

    public CustomNotOwnerException(String message) {
        super(message);
    }

    public CustomNotOwnerException(String message, Throwable cause) {
        super(message, cause);
    }
}
