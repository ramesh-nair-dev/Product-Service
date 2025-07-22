package com.example.productservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseClass {
    private String productTitle;
    private String productDescription;
    private double productPrice;
    private String productCategory;
    private String productImage;
}
