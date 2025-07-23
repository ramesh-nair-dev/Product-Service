package com.example.productservice.controllers;

import com.example.productservice.dtos.product.GetAllProductResponseDTO;
import com.example.productservice.dtos.product.CreateProductRequestDTO;
import com.example.productservice.dtos.product.CreateProductResponseDTO;
import com.example.productservice.dtos.product.GetProductDTO;
import com.example.productservice.models.Product;
import com.example.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    public GetAllProductResponseDTO getAllProducts(){
        // This method will return all the products
        // We will use @GetMapping to handle GET requests
        // and return a list of products from the database or any other source
        // This is typically used to retrieve a collection of resources
        // We will create a DTO named FakeStoreGetAllProductResponseDTO
        // which will contain a list of GetProductDTO
        // GetProductDTO will have the same fields as the Product model
        // We will use the ProductService to get all products
        // and then map the response to the FakeStoreGetAllProductResponseDTO
        // We will use the GetProductDTO to map the response from the Product model to the DTO

        GetAllProductResponseDTO responseDTO = new GetAllProductResponseDTO();
        List<GetProductDTO> responseList = new ArrayList<>();
        List<Product> productList = productService.getAllProducts();
        for(Product product : productList){
            responseList.add(GetProductDTO.fromProduct(product));
        }
        responseDTO.setGetProductDTOList(responseList);

        return responseDTO;
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

/**
 * Now we implement the Get all products api what will things we will need to implement this api?
 *  - A method in ProductService to get all products
 *  - A method in ProductController to handle the GET request
 *  - A DTO named FakeStoreGetAllProductsResponseDTO to map the response from the Fake Store API
 *  What we will do is that we will create two DTOs:
 *    - createProductDTO: This will be used to create a new product
 *    - GetProductDTO : This will be used to get a product by id
 *  - Let's say we want to get all product from the Fake Store API, we will create a DTO named FakeStoreGetAllProductsResponseDTO
 *  - Inside FakeStoreGetAllProductsResponseDTO we will have a list of GetProductDTO
 *  - GetProductDTO will have the same fields as the Product model which will used to display the product details
 *
 */



