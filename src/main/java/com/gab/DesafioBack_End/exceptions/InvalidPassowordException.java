package com.gab.DesafioBack_End.exceptions;

public class InvalidPassowordException extends RuntimeException {
    public InvalidPassowordException(String message) {
        super(message);
    }
}
