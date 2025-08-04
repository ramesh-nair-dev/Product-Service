package com.example.productservice.controllers;

import com.example.productservice.dtos.product.*;
import com.example.productservice.models.Product;
import com.example.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;


    public ProductController(
            @Value("${app.service.bean-name}") String serviceName,
            ApplicationContext context) {
        this.productService = (ProductService) context.getBean(serviceName);
    }

    @PostMapping("")
    public CreateProductResponseDTO createProduct(@RequestBody CreateProductRequestDTO createProductRequestDTO) {
        // This method will create a new product
        // We will use @PostMapping to handle POST requests
        // and create a new product in the database or any other source
        // This is typically used to create a new resource
        // The request body will contain the details of the product to be created

        Product product = productService.createProduct(createProductRequestDTO.toProduct());
        return CreateProductResponseDTO.createProductResponseDTO(product);
    }

    @GetMapping("")
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

    @GetMapping("/{id}")
    public GetProductDTO getSingleProduct(@PathVariable("id") Long id){
        // This method will return the product with the given id
        // We will use @PathVariable to get the id from the url
        // and then we will return the product from the database or any other source
        // We will use the ProductService to get the product by id

        Product product = productService.getSingleProduct(id);
        return GetProductDTO.fromProduct(product);

    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id){
        // This method will delete the product with the given id
        // We will use @PathVariable to get the id from the url
        // and then we will delete the product from the database or any other source
        // We will use the ProductService to delete the product by id
        productService.deleteProduct(id);
    }

    @PatchMapping("/{id}")
    public GetProductDTO updateProduct(@PathVariable("id") Long id, @RequestBody CreateProductDTO createProductDTO){
        // This method will update the product with the given id
        // We will use @PathVariable to get the id from the url
        // and then we will update the product in the database or any other source
        // The request body will contain the updated details of the product
        // We will use the ProductService to update the product
        // and then return the updated product in the response body
        // We will use the CreateProductDTO to map the request body to the Product model

        Product updatedProduct = productService.updateProduct(id, CreateProductDTO.toProduct(createProductDTO));

        return GetProductDTO.fromProduct(updatedProduct);

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
 * Now we implement PatchMapping suppose we have a product with some id we need to update the product
 *  - We will use @PatchMapping to handle the PATCH request
 *  - We will use @PathVariable to get the id from the url
 *  - We will use @RequestBody to get the updated product details from the request body
 *  - We will use the ProductService to update the product
 *  - We will return the updated product in the response body ,we will send for this product id , i want to change these
 *    details like product title, product price, product description, product category, product image etc in createProductDTO
 *
 *
 */



