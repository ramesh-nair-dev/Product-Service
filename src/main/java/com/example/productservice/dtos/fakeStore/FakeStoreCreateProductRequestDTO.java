package com.example.productservice.dtos.fakeStore;

import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class FakeStoreCreateProductRequestDTO {
    private String title;
    private double price;
    private String description;
    private Category category;
    private String image;

    public static FakeStoreCreateProductRequestDTO fromProduct(Product product) {
        FakeStoreCreateProductRequestDTO requestDTO = new FakeStoreCreateProductRequestDTO();
        requestDTO.setTitle(product.getProductTitle());
        requestDTO.setPrice(product.getProductPrice());
        requestDTO.setDescription(product.getProductDescription());
        requestDTO.setCategory(product.getCategory());
        requestDTO.setImage(product.getProductImage());
        return requestDTO;
    }
}

/**
 * This is FakeStoreCreateProductRequestDTO class which will be used to create a new product.
 * Why we need this class?
 * Because the Fake Store API has a different structure for creating products compared to our internal Product model.
 * This DTO will be used to map the request body from the Fake Store API to our internal Product model.
 */
