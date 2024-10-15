package org.example.possystembackendspring.exception;

public class OrderDetailsNotFoundException extends RuntimeException{
    public OrderDetailsNotFoundException() {
        super();
    }

    public OrderDetailsNotFoundException(String message) {
        super(message);
    }

    public OrderDetailsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
