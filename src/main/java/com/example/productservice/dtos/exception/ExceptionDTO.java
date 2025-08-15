package com.example.productservice.dtos.exception;

import com.example.productservice.dtos.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionDTO {
    private String message;
    private ResponseStatus responseStatus;
    private String resolutionDetails;
}
