package com.example.productservice.dtos.product;

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
}
