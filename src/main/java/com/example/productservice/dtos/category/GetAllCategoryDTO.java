package com.example.productservice.dtos.category;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetAllCategoryDTO {
    private List<GetCategoryDTO> categories;
}
