package com.example.productservice.dtos.exception;

import com.example.productservice.dtos.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductNotFoundExceptionDTO {
    private Long id;
    private String message;
    private String resolutionDetails;
    private ResponseStatus responseStatus;
}
