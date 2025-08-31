package com.example.productservice.service.sortingService;

import com.example.productservice.models.Product;

import java.util.List;

public interface Sorter {
    List<Product> sortBy(List<Product> products);
}
