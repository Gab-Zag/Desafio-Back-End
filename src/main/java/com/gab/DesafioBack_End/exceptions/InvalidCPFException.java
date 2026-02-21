package com.gab.DesafioBack_End.exceptions;

public class InvalidCPFException extends RuntimeException{
    public InvalidCPFException(String message){
        super(message);
    }
}
