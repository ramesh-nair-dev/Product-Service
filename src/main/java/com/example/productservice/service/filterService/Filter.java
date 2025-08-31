package com.example.productservice.service.filterService;

import com.example.productservice.models.Product;

import java.util.List;

public interface Filter {
    List<Product> apply(
            List<Product> products,
            List<String> allowedValues
    );
}

/**
 * 1. This is filter Interface what it will do is it will take List<Product>
 *    and it will return List<Product> after applying some filter logic
 *
 * 2. In the input we are taking two parameters , List<Product> products and List<String> allowedValues
 *    The first parameter is the list of products that we want to filter
 *    The second parameter is the list of allowed values for the filter
 *
 *
 *
 *
 */