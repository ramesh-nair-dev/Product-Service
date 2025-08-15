package com.example.productservice.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String message) {
        super(message);
    }
}

// This exception is thrown when a product is not found in the database.
// It extends the Exception class and takes a message as a parameter.
// The message can be used to provide more context about the error.
// This exception can be caught and handled in the ProductServiceExceptionHandler class.
