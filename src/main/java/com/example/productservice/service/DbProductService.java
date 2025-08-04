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

        CategoryCreation(product, product);

        return productRepository.save(product);

    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(Long id , Product product) {
        /**
         * How we will update the product?
         * 1. We will first check if the product with the given id exists in the database.
         * 2. We have product object from the controller which we which contain the updated details of the product.
         * 3. We also have the id of the product which we want to update.
         * 4. First we find the product by id from the database.
         * 5. If the product exists, we will update the product details with the new details from the product object.
         * 6. Finally we will save the updated product object in the database and return it.
         */

        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()){
            throw new RuntimeException("Product with id " + id + " not found");
        }
        Product existingProduct = productOptional.get();
        if(product.getProductTitle() != null){
            existingProduct.setProductTitle(product.getProductTitle());
        }
        if(product.getProductPrice() != null){
            existingProduct.setProductPrice(product.getProductPrice());
        }
        if(product.getProductDescription() != null){
            existingProduct.setProductDescription(product.getProductDescription());
        }
        if(product.getProductImage() != null){
            existingProduct.setProductImage(product.getProductImage());
        }
        if(product.getCategory().getCategoryName() != null){

            CategoryCreation(product, existingProduct);
            // What if check here if the category exists in the database or not we find it by name
            // If the category does not exist in the database , we will create a new category object and save it in the database
            // If the category exists in the database , we will get the category object from the database

        }
        return productRepository.save(existingProduct);
    }

    private void CategoryCreation(Product product, Product existingProduct) {
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
            existingProduct.setCategory(category);
            // We are setting the category object in the product object
        }
        else{
            // If the category exists in the database , we will get the category object from the database
            Category category = categoryOptional.get();
            // We are getting the category object from the Optional<Category>
            // Now we will set the category object in the product object
            existingProduct.setCategory(category);
        }
    }

    @Override
    public Product getSingleProduct(Long id) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }
}
