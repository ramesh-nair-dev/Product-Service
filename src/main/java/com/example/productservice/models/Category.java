package com.example.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity

public class Category extends BaseClass{
    private String categoryName;
    private String categoryDescription;
    @OneToMany(mappedBy = "category")
    private List<Product> allProducts;
    @OneToMany
    private List<Product> topProducts;
    @OneToOne
    private SubCategory subCategory;
}

/**
 * Category class represents a category entity in the product service.
 * It extends the BaseClass which contains common fields like id, createdAt, and updatedAt.
 * It has fields for category name, description, and lists of products.
 * It uses Lombok annotations for boilerplate code reduction and JPA annotations for ORM mapping.
 * It is annotated with @Entity to indicate that it is a JPA entity.
 * It uses @OneToMany annotation to establish a one-to-many relationship with the Product entity.
 * This allows a category to have multiple products associated with it, both for all products and top products.
 *
 * See already mapped category in product entity , for allProducts we write mappedBy = "category" to indicate that the Product entity has a field named category that maps to this Category entity.
 * For understanding the concept how query from an object attribute's attribute we created a subCategory object in Category class
 */
