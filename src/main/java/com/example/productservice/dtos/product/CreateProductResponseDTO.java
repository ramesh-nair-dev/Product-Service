package com.example.productservice.dtos.product;

import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductResponseDTO {
    private long id;
    private String productTitle;
    private double productPrice;
    private String productDescription;
    private Category category;
    private String productImage;

    public static CreateProductResponseDTO createProductResponseDTO(Product product) {
        CreateProductResponseDTO createProductResponseDTO = new CreateProductResponseDTO();
        createProductResponseDTO.setId(product.getId());
        createProductResponseDTO.setProductTitle(product.getProductTitle());
        createProductResponseDTO.setProductPrice(product.getProductPrice());
        createProductResponseDTO.setProductDescription(product.getProductDescription());
        createProductResponseDTO.setCategory(product.getCategory());
        createProductResponseDTO.setProductImage(product.getProductImage());
        return createProductResponseDTO;
    }
}
/**
 * This is CreateProductResponseDTO class which will be used to return the response after creating a new product.
 * It contains the necessary fields that are required to return the product details after creation.
 * It will be used in the ProductController to handle the response body when creating a new product.
 * It is a Data Transfer Object (DTO) that will be used to transfer data between the server and the client.
 * It is typically used to encapsulate the data that is sent in the response body after creating a new resource.
 *
 * Why we use DTOs?
 * DTOs are used to transfer data between different layers of the application, such as between the controller and the service layer.
 * DTOs help in decoupling the layers and provide a clear contract for the data that is being transferred.
 * DTOs also help in validating the data before it is sent to the service layer, ensuring that only valid data is processed.
 * DTOs can also be used to reduce the amount of data that is sent over the network by only including the necessary fields.
 * For example if in amazon in user table they store calc and keep the info when this user will buy next item , crucial information
 * like this when someone request for user details we don't want to send this info to the client, so we can create a DTO
 */
