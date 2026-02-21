package com.gab.DesafioBack_End.exceptions;

public class InvalidIDSenderException extends RuntimeException {
    public InvalidIDSenderException(String message) {
        super(message);
    }
}
