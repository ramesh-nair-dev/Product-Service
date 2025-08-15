package com.example.productservice.service;

import com.example.productservice.exceptions.CategoryNotFoundException;
import com.example.productservice.models.Category;
import com.example.productservice.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DbCategoryService implements CategoryService {
    private final CategoryRepository categoryRepository;

    public DbCategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category createCategory(Category category) {
        if (category == null || category.getCategoryName() == null || category.getCategoryName().isEmpty()) {
            throw new RuntimeException("Category name cannot be null or empty");
        }
        Optional<Category> categoryOptional = categoryRepository.findByCategoryName(category.getCategoryName());
        if (categoryOptional.isPresent()) {
            throw new RuntimeException("Category with name '" + category.getCategoryName() + "' already exists");
        }
        return categoryRepository.save(category);
    }

    @Override
    public Category getSingleCategory(Long categoryId) throws CategoryNotFoundException {
        if(categoryId == null) {
            throw new RuntimeException("Category ID cannot be null");
        }
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if (categoryOptional.isEmpty()) {
            throw new CategoryNotFoundException("Category with ID " + categoryId + " not found");
        }
        return categoryOptional.get();
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        if(categoryList.isEmpty()) {
            throw new RuntimeException("No categories found");
        }
        return categoryList;
    }

    @Override
    public Category updateCategory(Long categoryId, Category category) throws CategoryNotFoundException {
    if (categoryId == null || category == null) {
        throw new RuntimeException("Category ID and category cannot be null");
    }
    Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
    if (categoryOptional.isEmpty()) {
        throw new CategoryNotFoundException("Category with ID " + categoryId + " not found");
    }
    Category existingCategory = categoryOptional.get();

    if(category.getCategoryName() != null){
        existingCategory.setCategoryName(category.getCategoryName());
    }
    if(category.getCategoryDescription() != null){
        existingCategory.setCategoryDescription(category.getCategoryDescription());
    }
    return categoryRepository.save(existingCategory);
    }

    @Override
    public void deleteCategory(Long categoryId) throws CategoryNotFoundException {
        if(categoryId == null) {
            throw new RuntimeException("Category ID cannot be null");
        }
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if (categoryOptional.isEmpty()) {
            throw new CategoryNotFoundException("Category with ID " + categoryId + " not found");
        }
        // If the category exists, we proceed to delete it
        categoryRepository.deleteById(categoryId);
    }


}
