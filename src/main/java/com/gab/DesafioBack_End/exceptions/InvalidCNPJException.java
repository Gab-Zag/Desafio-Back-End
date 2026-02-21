package com.gab.DesafioBack_End.exceptions;

public class InvalidCNPJException extends RuntimeException {
    public InvalidCNPJException(String message) {
        super(message);
    }
}
