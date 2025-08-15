package com.example.productservice.dtos.category;

import com.example.productservice.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetCategoryDTO  {
    private Long categoryId;
    private String categoryName;
    private String categoryDescription;

    public static GetCategoryDTO fromCategory(Category category) {
        GetCategoryDTO getCategoryDTO = new GetCategoryDTO();
        getCategoryDTO.setCategoryId(category.getId());
        getCategoryDTO.setCategoryName(category.getCategoryName());
        getCategoryDTO.setCategoryDescription(category.getCategoryDescription());
        return getCategoryDTO;
    }
}


