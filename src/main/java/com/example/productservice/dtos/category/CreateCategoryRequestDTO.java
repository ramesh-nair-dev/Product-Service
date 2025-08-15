package com.example.productservice.dtos.category;

import com.example.productservice.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCategoryRequestDTO {
    private String categoryName;
    private String categoryDescription;

    public static Category toCategory(CreateCategoryRequestDTO createCategoryRequestDTO) {
        Category category = new Category();
        category.setCategoryName(createCategoryRequestDTO.getCategoryName());
        category.setCategoryDescription(createCategoryRequestDTO.getCategoryDescription());
        return category;
    }
}

/**
 * CreateCategoryDTO is a Data Transfer Object (DTO) used for creating a new category.
 * It contains fields for the category name and description.
 * This DTO is used to transfer data between the client and server when creating a new category.
 * It uses Lombok annotations to automatically generate getter and setter methods.
 */

