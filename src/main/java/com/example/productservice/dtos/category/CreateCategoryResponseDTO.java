package com.example.productservice.dtos.category;

import com.example.productservice.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCategoryResponseDTO {
    private Long categoryId;
    private String categoryName;
    private String categoryDescription;

    public static CreateCategoryResponseDTO fromCategory(Category category) {
        CreateCategoryResponseDTO createCategoryResponseDTO = new CreateCategoryResponseDTO();
        createCategoryResponseDTO.setCategoryId(category.getId());
        createCategoryResponseDTO.setCategoryName(category.getCategoryName());
        createCategoryResponseDTO.setCategoryDescription(category.getCategoryDescription());
        return createCategoryResponseDTO;
    }
}
