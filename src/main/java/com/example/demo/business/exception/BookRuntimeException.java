package com.example.demo.business.exception;

public class BookRuntimeException extends RuntimeException {

    public BookRuntimeException(){super();}

    public BookRuntimeException(String message) {super(message);}

    public BookRuntimeException(String message, Throwable exception) {
        super(message, exception);
    }

}
