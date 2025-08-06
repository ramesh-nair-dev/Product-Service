package com.example.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionDTO {
    private String message;
    private ResponseStatus responseStatus;
    private String resolutionDetails;
}
