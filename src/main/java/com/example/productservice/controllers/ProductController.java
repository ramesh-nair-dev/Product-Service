package com.example.productservice.controllers;

import com.example.productservice.dtos.CreateProductRequestDTO;
import com.example.productservice.dtos.CreateProductResponseDTO;
import com.example.productservice.models.Product;
import com.example.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    private final ProductService productService;


    public ProductController(
            @Value("${app.service.bean-name}") String serviceName,
            ApplicationContext context) {
        this.productService = (ProductService) context.getBean(serviceName);
    }

    @PostMapping("/products")
    public CreateProductResponseDTO createProduct(@RequestBody CreateProductRequestDTO createProductRequestDTO) {
        // This method will create a new product
        // We will use @PostMapping to handle POST requests
        // and create a new product in the database or any other source
        // This is typically used to create a new resource
        // The request body will contain the details of the product to be created

        Product product = productService.createProduct(createProductRequestDTO.toProduct());
        return CreateProductResponseDTO.createProductResponseDTO(product);
    }

    @GetMapping("/products")
    public void getAllProducts(){
        // This method will return all the products
        // We will use @GetMapping to handle GET requests
        // and return a list of products from the database or any other source
        // This is typically used to retrieve a collection of resources

    }

    @GetMapping("/products/{id}")
    public void getProduct(@PathVariable("id") Long id){
        // This method will return the product with the given id
        // We will use @PathVariable to get the id from the url
        // and then we will return the product from the database or any other source

    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable("id") Long id){
        // This method will delete the product with the given id
        // We will use @PathVariable to get the id from the url
        // and then we will delete the product from the database or any other source
    }





}


/**
 * This is ProductController class to which client will pass the request.
 * So how do we handle the request?
 * - We create method right
 *     1. we will have createProduct method
 *     2. we will have getProduct one single product method
 *     3. we will have getAllProducts method
 *     4. we will have deleteProduct method
 *   and many other methods
 *
 *   What does RestController do?
 *    - It is a convenience annotation that combines @Controller and @ResponseBody.
 *    - @RestController creates web APIs that return data (like JSON) instead of web pages — perfect for building RESTful services.
 *
 *   1 . We want to create a product , we will need to make post request right i have these detail create the product
 *         - @PostMapping("/products") - this is the endpoint we will use to create a product
 *         What is happening here?
 *          - When we make a post request to /products, this method will be called.
 *          - PostMapping is a Spring annotation that maps HTTP POST requests onto specific handler methods.\
 *          - In this when user give the url and makes type Post and make request spring will detect that hey url and type is Post i should
 *            call this method.
 *
 *    2 . Next method we want to create is getAllProduct method in this case we will use @GetMapping
 *        why @GetMapping?
 *        - @GetMapping is used to handle GET requests, which are typically used to retrieve data from the server.
 *
 *    3. Next method we want to create is get one particular product
 *        - we will use @GetMapping because we want to get one product
 *        - but in url we will have some id like /products/{id} which we will get from user , user will click product
 *          and we will get the id of that product and we will use that id to get the product.
 *        - An api url has path variable like /products/{id} which is a placeholder for the actual id of the product.
 *        - An api url can have query parameters like /products?id=1&name=product1 which is used to filter the products.
 *        sometime if we can pass the request body in path variable itself if thee short and our name of api is still clean
 *        if the request body is large then we will use request body to pass the data. because in http request
 *        we have {
 *            type : "POST",  ---> type of the request
 *            body : { ---> body of the request  } ---> it can json xml anything
 *            url : "/products/{id}" ---> url of the request
 *        }
 *
 *        How do we get the id from the url?
 *        - We can use @PathVariable annotation to get the id from the url.
 *        - @PathVariable is used to extract values from the URI template.
 *        - Now we have id from that we can ge the product from the database or any other source and return it to client
 *
 *     4. Next method we want to create is deleteProduct method
 *          - We will use @DeleteMapping to handle DELETE requests, which are typically used to delete data from the server.
 *          - We will use @PathVariable to get the id from the url and then we will delete the product from the database or any other source.
 *          - This is typically used to delete a resource.
 *
 *
 *
 *      5. Now let's say i want to create request type Tony can i do that?
 *         - Yes we can do that by using @RequestMapping annotation.
 *         - @RequestMapping is a versatile annotation that can be used to map HTTP requests to handler methods.
 *         @RequestMapping(name="Tony" , value ="/products/")
 *         public string magic(){
 *         return "Hello Tony";
 *
 *        }
 *
 *        In this case we are mapping the request to /products/ with type Tony.
 *        in postman if i make a request to /products/ with type Tony
 *        this method will be called and it will return "Hello Tony".
 *
 *
 *        We  will implement the the first method createProduct
 *         - To create a product , our function will need some details like name, price, description etc.
 *         - Then will send that details to Service layer which will handle the logic of creating a product.
 *
 *
 *      6. We have created DTO's and service layer , but there is problem we have 2 productservice classes
 *         spring will not know which one to use to avoid there is two ways
 *          - We can use @Primary annotation on the class which we want to use as default implementation
 *            - @Primary annotation is used to indicate that a bean should be given preference when multiple candidates are qualified to autowire a single-valued dependency.
 *            - If want to use DbProductService as default implementation we can add @Primary annotation on it.
 *          - We can use @Qualifier annotation to specify which implementation we want to use
 *            - @Qualifier annotation is used to provide a specific bean when multiple candidates are available.
 *            - We can use @Qualifier("dbProductService") to specify that we want to use DbProductService implementation.
 *            - But there is one problem with this approach, we have to specify the implementation every time we want to use it.
 *            - what we can do we have a global variable and will in that we store which service to use
 *
 *
 */
