package com.example.demo.business.exception;

public class PublicationRuntimeException extends RuntimeException{

    public PublicationRuntimeException(){super();}

    public PublicationRuntimeException(String message){super(message);}

    public PublicationRuntimeException(String message,Throwable exception){super(message,exception);}
}
