package com.example.productservice.controllers;

import com.example.productservice.dtos.category.CreateCategoryRequestDTO;
import com.example.productservice.dtos.category.CreateCategoryResponseDTO;
import com.example.productservice.dtos.category.GetCategoryDTO;
import com.example.productservice.exceptions.CategoryNotFoundException;
import com.example.productservice.models.Category;
import com.example.productservice.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    // This controller will handle requests related to categories
    // We can add methods to handle CRUD operations for categories
    // For example, we can add methods to create, read, update, and delete categories
    // We can also add methods to get all categories or get a single category by ID
    // The implementation will depend on the requirements of the application
    // We can use @GetMapping, @PostMapping, @PutMapping, and @DeleteMapping annotations
    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("")
    public ResponseEntity<CreateCategoryResponseDTO> createCategory(@RequestBody CreateCategoryRequestDTO createCategoryRequestDTO) {
        // Method to create a new category
        // This method will handle POST requests to create a new category
        // The request body will contain the details of the category to be created
        // We will use the CreateCategoryDTO to map the request body to the DTO
        // Here we would typically call a service method to save the category to the database
        Category category = categoryService.createCategory(CreateCategoryRequestDTO.toCategory(createCategoryRequestDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(CreateCategoryResponseDTO.fromCategory(category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetCategoryDTO> getSingleCategory(@PathVariable("id") Long id) throws CategoryNotFoundException {
        // Method to get a single category by ID
        // This method will handle GET requests to retrieve a single category
        // We can use @GetMapping("/{id}") to handle requests with a specific category ID
        // We would typically call a service method to retrieve the category from the database

        Category category = categoryService.getSingleCategory(id);
        return ResponseEntity.status(HttpStatus.OK).body(GetCategoryDTO.fromCategory(category));
    }
}
