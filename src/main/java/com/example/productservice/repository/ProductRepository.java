package com.example.productservice.repository;

import com.example.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
    // JpaRepository provides methods for CRUD operations
    // No additional methods are needed unless specific queries are required

}
