package com.example.productservice.dtos.fakeStore;

import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class FakeStoreGetProductResponseDTO {
    private long id;
    private String title;
    private double price;
    private String description;
    private Category category;
    private String image;


    public static Product toProduct(FakeStoreGetProductResponseDTO fakeStoreGetProductResponseDTO) {
        Product product = new Product();
        product.setId(fakeStoreGetProductResponseDTO.getId());
        product.setProductTitle(fakeStoreGetProductResponseDTO.getTitle());
        product.setProductPrice(fakeStoreGetProductResponseDTO.getPrice());
        product.setProductDescription(fakeStoreGetProductResponseDTO.getDescription());
        product.setCategory(fakeStoreGetProductResponseDTO.getCategory());
        product.setProductImage(fakeStoreGetProductResponseDTO.getImage());
        return product;
    }
}
