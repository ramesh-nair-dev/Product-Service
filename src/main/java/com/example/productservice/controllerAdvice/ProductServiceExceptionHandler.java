package com.example.productservice.controllerAdvice;

import com.example.productservice.dtos.ExceptionDTO;
import com.example.productservice.dtos.ProductNotFoundExceptionDTO;
import com.example.productservice.dtos.ResponseStatus;
import com.example.productservice.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductServiceExceptionHandler {
    // This class is used to handle exceptions globally for the ProductService application
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionDTO> handleRunTimeException(Exception e) {
        // This method will handle exceptions thrown by the ProductService
        // We can log the exception, return a custom error response, etc.
        // For now, we will just print the stack trace
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setMessage(e.getMessage());
        exceptionDTO.setResponseStatus(ResponseStatus.Failure);
        exceptionDTO.setResolutionDetails("Please try again later or contact support if the issue persists.");
        return new ResponseEntity<>(
                exceptionDTO,
                HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ProductNotFoundExceptionDTO> handleProdNotFoundException(Exception e){
        // This method will handle ProductNotFoundException
        // We can log the exception, return a custom error response, etc.

        ProductNotFoundExceptionDTO productNotFoundExceptionDTO = new ProductNotFoundExceptionDTO();
        String productId = e.getMessage();
        productNotFoundExceptionDTO.setId(Long.valueOf(productId));
        productNotFoundExceptionDTO.setMessage("Product not found");
        productNotFoundExceptionDTO.setResponseStatus(ResponseStatus.Failure);
        productNotFoundExceptionDTO.setResolutionDetails("Please check the product ID and try again.");
        return new ResponseEntity<>(productNotFoundExceptionDTO, HttpStatus.NOT_FOUND);


    }
}
