package com.example.demo.business.exception;

public class PublisherRuntimeException extends RuntimeException{

    public PublisherRuntimeException(){super();}
    public PublisherRuntimeException(String message){super(message);}
    public PublisherRuntimeException(String message, Throwable exception){super(message,exception);}
}
