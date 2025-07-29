package com.example.productservice.service;

import com.example.productservice.models.Product;
import com.example.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("dbProductService")
public class DbProductService implements ProductService {
    private final ProductRepository productRepository;

    public DbProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
        // The save method will handle both create and update operations
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product updateProduct(Long id , Product product) {
        return null;
    }

    @Override
    public Product getSingleProduct(Long id) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }
}
