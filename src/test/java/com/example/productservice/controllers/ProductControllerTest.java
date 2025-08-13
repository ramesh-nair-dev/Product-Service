package com.example.productservice.controllers;

import com.example.productservice.dtos.product.GetProductDTO;
import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.service.DbProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockitoBean
    private DbProductService productService;

    @Test
    void getSingleProduct() throws ProductNotFoundException {
        // Arrange
        Category category = new Category();
        category.setCategoryName("Electronics");

        Long productId = 1L;
        Product expectedProduct = new Product();
        expectedProduct.setId(productId);
        expectedProduct.setProductTitle("Product Title");
        expectedProduct.setProductDescription("Product Description");
        expectedProduct.setCategory(category);
        expectedProduct.setProductPrice(100.0);

        when(productService.getSingleProduct(productId)).thenReturn(expectedProduct);

        // Act
        ResponseEntity<GetProductDTO> response = productController.getSingleProduct(productId);

        // Assert
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isEqualTo(productId);
        assertThat(response.getBody().getProductTitle()).isEqualTo("Product Title");
        assertThat(response.getBody().getProductDescription()).isEqualTo("Product Description");
        assertThat(response.getBody().getProductPrice()).isEqualTo(100.0);
        assertThat(response.getBody().getProductCategoryName()).isEqualTo("Electronics");

    }
}
