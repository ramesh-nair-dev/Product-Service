package com.example.productservice.service;

import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.repository.CategoryRepository;
import com.example.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("dbProductService")
public class DbProductService implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public DbProductService(ProductRepository productRepository , CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product createProduct(Product product) {
        /**
         * We receive a product object from the controller , we need to save this product object in the database
         * We will use the productRepository to save the product object
         * Before saving the product object , we will need to store category object in the database
         * because we have category object in the product object , after that we will save the product object
         */

        String categoryName = product.getCategory().getCategoryName();
        // We have getting product object from the controller from that product object we are getting category name

        Optional<Category> categoryOptional = categoryRepository.findByCategoryName(categoryName);
        // We are checking if the category exists in the database or not

        if(categoryOptional.isEmpty()){
            // If the category does not exist in the database , we will create a new category object and save it in the database
            Category category = new Category();
            category.setCategoryName(categoryName);
            // We are setting the category name in the category object
            categoryRepository.save(category);
            // Now we will save the category object in the database
            product.setCategory(category);
            // We are setting the category object in the product object
        }
        else{
            // If the category exists in the database , we will get the category object from the database
            Category category = categoryOptional.get();
            // We are getting the category object from the Optional<Category>
            // Now we will set the category object in the product object
            product.setCategory(category);
        }

        return productRepository.save(product);

    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(Long id , Product product) {
        return null;
    }

    @Override
    public Product getSingleProduct(Long id) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }
}
