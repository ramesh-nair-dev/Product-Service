package com.example.productservice.repository;

import com.example.productservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findByCategoryName(String categoryName);
    // This method retrieves a category by its name, returning an Optional<Category>
    // Why we are returning Optional<Category>?
    // Because the category may not exist in the database, so we use Optional to handle the case where no category is found.

}
