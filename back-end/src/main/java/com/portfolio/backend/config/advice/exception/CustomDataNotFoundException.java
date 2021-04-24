package com.portfolio.backend.config.advice.exception;

public class CustomDataNotFoundException extends RuntimeException{

    public CustomDataNotFoundException() {
        super();
    }

    public CustomDataNotFoundException(String message) {
        super(message);
    }

    public CustomDataNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
