package com.example.productservice.controllerAdvice;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ProductServiceExceptionHandler {
    public void handleException(Exception e) {
        // This method will handle exceptions thrown by the ProductService
        // We can log the exception, return a custom error response, etc.
        // For now, we will just print the stack trace
        e.printStackTrace();
    }
}
