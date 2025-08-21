package com.example.productservice.exceptions;

public class UnAuthorisedAccessException extends Exception {
    public UnAuthorisedAccessException(String message) {
        super(message);
    }
}
