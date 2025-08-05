package com.example.productservice.repository;

import com.example.productservice.models.Product;
import com.example.productservice.service.CustomQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    // JpaRepository provides methods for CRUD operations
    // No additional methods are needed unless specific queries are required
    /**
     * What we want to achieve here is ?
     * We want to get all product by Category name but Product model has category object and that
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

    // We can write custom JPA queries if needed
    @Query("select p from Product p where p.category.subCategory.subCategoryName = : subCategoryName")
    List<Product> customJPAProductsBySubCategoryName(@Param("subCategoryName") String subCategoryName);
    // This method uses a custom JPQL query to retrieve products by subcategory name
    // It uses the @Query annotation to define the query and the @Param annotation to bind the method parameter to the query parameter
    // The way we write the query is similar to SQL but uses entity names and attributes instead of table names and columns

    // We can also write custom native SQL queries if needed
    @Query(value= CustomQueries.GET_ALL_PRODUCTS_BY_CATEGORY_NAME, nativeQuery = true)
    List<Product> customNativeQuery(@Param("categoryName") String categoryName);
    // This method uses a custom native SQL query to retrieve products by category name
    // It uses the @Query annotation with nativeQuery = true to indicate that the query is a native SQL query
    // The query is defined in the CustomQueries interface, which can be used to centralize custom queries
    // The @Param annotation is used to bind the method parameter to the query parameter

}
