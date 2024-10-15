package org.example.possystembackendspring.exception;

public class DataPersistsException extends RuntimeException{
    public DataPersistsException() {
        super();
    }

    public DataPersistsException(String message) {
        super(message);
    }

    public DataPersistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
