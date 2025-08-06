package com.example.productservice.service;

public interface CustomQueries {
String GET_ALL_PRODUCTS_BY_CATEGORY_NAME = """
SELECT * FROM product p
JOIN category c ON p.category_id = c.id
WHERE c.category_name = :categoryName
""";
}