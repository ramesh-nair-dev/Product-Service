package com.example.productservice.service.filterService;

import com.example.productservice.models.Product;

import java.util.List;

public class RAMFilter implements Filter {

    @Override
    public List<Product> apply(
            List<Product> products,
            List<String> allowedValues)
    {
        return List.of();
    }

}
