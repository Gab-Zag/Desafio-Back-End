package com.gab.DesafioBack_End.exceptions;

public class InvalidEmailException extends RuntimeException{
    public InvalidEmailException(String message){
        super(message);
    }
}
