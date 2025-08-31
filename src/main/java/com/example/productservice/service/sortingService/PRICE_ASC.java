package com.example.productservice.service.sortingService;

import com.example.productservice.models.Product;

import java.util.List;

public class PRICE_ASC implements Sorter {
    @Override
    public List<Product> sortBy(List<Product> products) {
        return List.of();
    }
}

/**
 * In this class we are implementing the Sorter interface
 * We simply sort the products by price in ascending order
 */
