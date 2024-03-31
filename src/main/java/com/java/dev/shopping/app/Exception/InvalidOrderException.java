package com.java.dev.shopping.app.Exception;

public class InvalidOrderException extends RuntimeException{
    public InvalidOrderException(String message){
        super(message);
    }
}
