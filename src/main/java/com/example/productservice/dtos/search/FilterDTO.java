package com.example.productservice.dtos.search;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class FilterDTO {
    private String key;
    private List<String> values;
}
