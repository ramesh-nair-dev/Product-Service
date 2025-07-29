package com.example.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseClass {
    private String productTitle;
    private double productPrice;
    private String productDescription;
    @ManyToOne
    private Category category;
    private String productImage;
}


/**
 * Product class represents a product entity in the product service.
 * It extends the BaseClass which contains common fields like id, createdAt, and updatedAt.
 * It has fields for product title, price, description, category, and image.
 * It uses Lombok annotations for boilerplate code reduction and JPA annotations for ORM mapping.
 * It is annotated with @Entity to indicate that it is a JPA entity.
 * It uses @ManyToOne annotation to establish a many-to-one relationship with the Category entity.
 */
