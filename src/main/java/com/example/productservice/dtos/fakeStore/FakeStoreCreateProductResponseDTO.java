package com.example.productservice.dtos.fakeStore;

import com.example.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreCreateProductResponseDTO {
    private long id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;

  public static Product toProduct(FakeStoreCreateProductResponseDTO fakeStoreCreateProductResponseDTO) {
        Product product = new Product();
        product.setId(fakeStoreCreateProductResponseDTO.getId());
        product.setProductTitle(fakeStoreCreateProductResponseDTO.getTitle());
        product.setProductPrice(fakeStoreCreateProductResponseDTO.getPrice());
        product.setProductDescription(fakeStoreCreateProductResponseDTO.getDescription());
        product.setProductCategory(fakeStoreCreateProductResponseDTO.getCategory());
        product.setProductImage(fakeStoreCreateProductResponseDTO.getImage());
        return product;
    }
  }

/**
 * This is FakeStoreCreateProductResponseDTO class which will be used to map the response from the Fake Store API when creating a new product.
 * It contains the necessary fields that are returned by the Fake Store API when a product is created.
 * This FakeStoreCreateProductResponseDTO will be mapped to our internal Product model in the service layer.
 * It is a Data Transfer Object (DTO) that will be used to transfer data between the client and the server.
 */