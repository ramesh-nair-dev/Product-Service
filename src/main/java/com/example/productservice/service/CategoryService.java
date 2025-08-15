package com.example.productservice.service;

import com.example.productservice.exceptions.CategoryNotFoundException;
import com.example.productservice.models.Category;
import org.springframework.stereotype.Service;


public interface CategoryService {
  Category createCategory(Category category);
  Category getSingleCategory(Long categoryId) throws CategoryNotFoundException;
}
