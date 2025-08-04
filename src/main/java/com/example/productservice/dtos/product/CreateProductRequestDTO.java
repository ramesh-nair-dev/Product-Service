package com.example.productservice.dtos.product;

import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductRequestDTO {
    private String productTitle;
    private double productPrice;
    private String productDescription;
    private String productCategory;
    private String productImage;

    public Product toProduct(){
        Product product = new Product();
        Category category = new Category();
        product.setProductTitle(this.productTitle);
        product.setProductPrice(this.productPrice);
        product.setProductDescription(this.productDescription);
        product.setCategory(category);
        category.setCategoryName(this.productCategory);
        product.setProductImage(this.productImage);
        return product;
    }
}

/**
 * This is CreateProductRequestDTO class which will be used to create a new product.
 * It contains the necessary fields that are required to create a product.
 * It will be used in the ProductController to handle the request body when creating a new product.
 * It is a Data Transfer Object (DTO) that will be used to transfer data between the client and the server.
 * It is typically used to encapsulate the data that is sent in the request body when creating a new resource.
 */