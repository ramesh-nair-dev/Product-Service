package com.example.productservice.repository;

import com.example.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    // JpaRepository provides methods for CRUD operations
    // No additional methods are needed unless specific queries are required
    /**
     * What we want to achieve here is ?
     * We want to get all product by Category name right but Product model has category object and that
     * category object has name attribute
     */
    List<Product> findAllByCategory_CategoryName(String categoryName);
    // This method retrieves all products that belong to a specific category by its name

    /**
     * Let say that in our Category model we have other class object named as subCategory
     * This SubCategory class is attribute of Category class
     * SubCategory class has subCategoryName attribute
     * We can also write a method to get all products by subCategory name
     */
    List<Product> findAllByCategory_SubCategory_SubCategoryName(String subCategoryName);
    // This method retrieves all products that belong to a specific subcategory by its name



}
