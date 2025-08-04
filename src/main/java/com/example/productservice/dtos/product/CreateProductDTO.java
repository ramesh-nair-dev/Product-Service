package com.example.productservice.dtos.product;

import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductDTO {
    private String productTitle;
    private Double productPrice;
    private String productDescription;
    private String productCategoryName;
    private String productImage;

    public static Product toProduct(CreateProductDTO createProductDTO){
        Product product = new Product();
        Category category = new Category();
        product.setProductTitle(createProductDTO.getProductTitle());
        product.setProductPrice(createProductDTO.getProductPrice());
        product.setProductDescription(createProductDTO.getProductDescription());
        product.setCategory(category);
        category.setCategoryName(createProductDTO.getProductCategoryName());
        product.setProductImage(createProductDTO.getProductImage());
        return product;
    }
}
