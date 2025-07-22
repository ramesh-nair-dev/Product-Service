package com.example.productservice.service;

import com.example.productservice.dtos.FakeStoreCreateProductRequestDTO;
import com.example.productservice.dtos.FakeStoreCreateProductResponseDTO;
import com.example.productservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("FakeApiProductService")
public class FakeApiProductService implements ProductService {
    private final RestTemplate restTemplate;

    @Autowired
    public FakeApiProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public Product createProduct(Product product) {
        FakeStoreCreateProductRequestDTO requestDTO = new FakeStoreCreateProductRequestDTO();
        requestDTO.setTitle(product.getProductTitle());
        requestDTO.setPrice(product.getProductPrice());
        requestDTO.setDescription(product.getProductDescription());
        requestDTO.setCategory(product.getProductCategory());
        requestDTO.setImage(product.getProductImage());
        FakeStoreCreateProductResponseDTO responseDTO = restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                requestDTO,
                FakeStoreCreateProductResponseDTO.class
                );

        Product newProduct = new Product();
        assert responseDTO != null;
        newProduct.setId(responseDTO.getId());
        newProduct.setProductTitle(responseDTO.getTitle());
        newProduct.setProductPrice(responseDTO.getPrice());
        newProduct.setProductDescription(responseDTO.getDescription());
        newProduct.setProductCategory(responseDTO.getCategory());
        newProduct.setProductImage(responseDTO.getImage());
        return newProduct;
    }
}

/**
 * This is the FakeApiProductService class which implements the ProductService interface.
 * It uses the RestTemplate to make HTTP requests to the Fake Store API.
 * It is used to create a new product by sending a POST request to the Fake Store API.
 * It maps the response from the Fake Store API to our internal Product model.
 * This service is used when we want to interact with the Fake Store API to create products.
 *
 */
