package com.example.productservice.service;

import com.example.productservice.models.Product;
import org.springframework.stereotype.Service;

@Service("dbProductService")
public class DbProductService implements ProductService {
    @Override
    public Product createProduct(Product product) {
        return null;
    }
}
