package com.example.productservice.controllers;

import com.example.productservice.dtos.CreateProductRequestDTO;
import com.example.productservice.dtos.CreateProductResponseDTO;
import com.example.productservice.models.Product;
import com.example.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    private final ProductService productService;


    public ProductController(
            @Value("${app.service.bean-name}") String serviceName,
            ApplicationContext context) {
        this.productService = (ProductService) context.getBean(serviceName);
    }

    @PostMapping("/products")
    public CreateProductResponseDTO createProduct(@RequestBody CreateProductRequestDTO createProductRequestDTO) {
        // This method will create a new product
        // We will use @PostMapping to handle POST requests
        // and create a new product in the database or any other source
        // This is typically used to create a new resource
        // The request body will contain the details of the product to be created

        Product product = productService.createProduct(createProductRequestDTO.toProduct());
        return CreateProductResponseDTO.createProductResponseDTO(product);
    }

    @GetMapping("/products")
    public void getAllProducts(){
        // This method will return all the products
        // We will use @GetMapping to handle GET requests
        // and return a list of products from the database or any other source
        // This is typically used to retrieve a collection of resources

    }

    @GetMapping("/products/{id}")
    public void getProduct(@PathVariable("id") Long id){
        // This method will return the product with the given id
        // We will use @PathVariable to get the id from the url
        // and then we will return the product from the database or any other source

    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable("id") Long id){
        // This method will delete the product with the given id
        // We will use @PathVariable to get the id from the url
        // and then we will delete the product from the database or any other source
    }

}



