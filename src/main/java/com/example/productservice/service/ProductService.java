package com.example.productservice.service;

import com.example.productservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {
    Product createProduct(Product product);
    List<Product> getAllProducts();
    Product updateProduct(Long id , Product product);
}

/**
 * Why we made service layer as Interface?
 *  1. It allows us to define a contract for the service layer without tying it to a specific implementation.
 *  2. It enables us to create multiple implementations of the service layer if needed, such as for different data sources or business logic.
 *  3. In this case we are proxy server where client will make request to our server ,  we will make request to third party server to fullfill the request
 *  4. Later we don't want to use third server we want to user own database or any other service, we can just create a new implementation of this interface
 *  5. If we did not make an interface and did we would have created one implementation of service for third party api and another implementation for our own database
 *  6. This would have made the code tightly coupled and difficult to maintain or extend in the future.
 *  7. By using an interface, we can easily switch between different implementations without affecting the rest of the codebase.
 *  This is ProductService interface which will be used to define the contract for the product service.
 *
 */
