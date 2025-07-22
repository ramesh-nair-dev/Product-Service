package com.example.productservice.dtos;

import com.example.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class FakeStoreCreateProductRequestDTO {
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;
}

/**
 * This is FakeStoreCreateProductRequestDTO class which will be used to create a new product.
 * Why we need this class?
 * Because the Fake Store API has a different structure for creating products compared to our internal Product model.
 * This DTO will be used to map the request body from the Fake Store API to our internal Product model.
 */
