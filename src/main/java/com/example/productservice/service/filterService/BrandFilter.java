package com.example.productservice.service.filterService;

import com.example.productservice.models.Product;

import java.util.List;

public class BrandFilter implements Filter {

    @Override
    public List<Product> apply(
            List<Product> products,
            List<String> allowedValues
    ) {
        return List.of();
    }
}

/**
 * How to use this BrandFilter class?
 * It will take a list of products we want to filter
 * It will also take a list of allowed brand names
 *
 * For example if we have a list of products with different brand names
 * and we want to filter the products to only include those with brand names "Apple" and "Samsung"
 * we will pass the list of products and the list of allowed brand names to the apply method of this BrandFilter class
 *
 * The apply method will then return a new list of products that only includes those with the allowed brand names
 *
 * Suppose our input list of products is:
 * [
 *  {id: 1, name: "iPhone 13", brand: "Apple"},
 *  {id: 2, name: "Galaxy S21", brand: "Samsung"},
 *  {id: 3, name: "Pixel 6", brand: "Google"},
 *  {id: 4, name: "OnePlus 9", brand: "OnePlus"}
 *  ]
 *
 * And our list of allowed brand names is:
 * ["Apple", "Samsung"]
 *
 *
 *
 */
