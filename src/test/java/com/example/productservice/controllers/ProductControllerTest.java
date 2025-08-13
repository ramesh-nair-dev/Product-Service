package com.example.productservice.controllers;

import com.example.productservice.dtos.product.GetAllProductResponseDTO;
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

import java.util.ArrayList;
import java.util.List;

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

    @Test
    void getSingleProductNotFound() throws ProductNotFoundException {
        // Arrange
        Long productId = 999L; // Non-existent product ID
        when(productService.getSingleProduct(productId)).thenThrow(new ProductNotFoundException("Product not found"));

        // Act & Assert
        try {
            productController.getSingleProduct(productId);
        } catch (ProductNotFoundException e) {
            assertThat(e.getMessage()).isEqualTo("Product not found");
        }
    }

    @Test
    void getAllProducts() {
        //Arrange
        List<Product> productList = new ArrayList<>();
        Category category = new Category();
        category.setCategoryName("Electronics");
        Product product1 = new Product();
        product1.setId(1L);
        product1.setProductTitle("Product 1");
        product1.setProductDescription("Description 1");
        product1.setCategory(category);
        product1.setProductPrice(50.0);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setProductTitle("Product 2");
        product2.setProductDescription("Description 2");
        product2.setCategory(category);
        product2.setProductPrice(75.0);

        productList.add(product1);
        productList.add(product2);

        GetAllProductResponseDTO expectedResponse = new GetAllProductResponseDTO();

        List<GetProductDTO> expectedGetProductDTOList = new ArrayList<>();

        for(Product product : productList) {
            expectedGetProductDTOList.add(GetProductDTO.fromProduct(product));
        }

        expectedResponse.setGetProductDTOList(expectedGetProductDTOList);

        when(productService.getAllProducts()).thenReturn(productList);

        // Act
        ResponseEntity<GetAllProductResponseDTO> response = productController.getAllProducts();

        // Assert
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getGetProductDTOList()).isNotNull();
        assertThat(response.getBody().getGetProductDTOList().size()).isEqualTo(expectedGetProductDTOList.size());

    }
}
