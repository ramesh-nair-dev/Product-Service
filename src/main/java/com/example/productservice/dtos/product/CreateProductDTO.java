package com.example.productservice.dtos.product;

import com.example.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductDTO {
    private String productTitle;
    private double productPrice;
    private String productDescription;
    private String ProductCategory;
    private String productImage;

    public static Product toProduct(CreateProductDTO createProductDTO){
        Product product = new Product();
        product.setProductTitle(createProductDTO.getProductTitle());
        product.setProductPrice(createProductDTO.getProductPrice());
        product.setProductDescription(createProductDTO.getProductDescription());
        product.setProductCategory(createProductDTO.getProductCategory());
        product.setProductImage(createProductDTO.getProductImage());
        return product;
    }
}
