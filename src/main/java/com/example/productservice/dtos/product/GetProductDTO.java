package com.example.productservice.dtos.product;

import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetProductDTO {
    private long id;
    private String productTitle;
    private double productPrice;
    private String productDescription;
    private Category category;
    private String productImage;

    public static GetProductDTO fromProduct(Product product) {
        GetProductDTO dto = new GetProductDTO();
        dto.setId(product.getId());
        dto.setProductTitle(product.getProductTitle());
        dto.setProductPrice(product.getProductPrice());
        dto.setProductDescription(product.getProductDescription());
        dto.setCategory(product.getCategory());
        dto.setProductImage(product.getProductImage());
        return dto;
    }
}
