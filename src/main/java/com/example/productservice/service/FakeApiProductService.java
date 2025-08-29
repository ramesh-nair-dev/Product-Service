package com.example.productservice.service;

import com.example.productservice.dtos.fakeStore.FakeStoreCreateProductRequestDTO;
import com.example.productservice.dtos.fakeStore.FakeStoreCreateProductResponseDTO;
import com.example.productservice.dtos.fakeStore.FakeStoreGetProductResponseDTO;
import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("FakeApiProductService")
public class FakeApiProductService implements ProductService {
    private final RestTemplate restTemplate;
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public FakeApiProductService(
            RestTemplate restTemplate,
            RedisTemplate<String, Object> redisTemplate
    ) {
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
    }
    @Override
    public Product createProduct(Product product) {
        FakeStoreCreateProductRequestDTO requestDTO = new FakeStoreCreateProductRequestDTO();
        requestDTO.setTitle(product.getProductTitle());
        requestDTO.setPrice(product.getProductPrice());
        requestDTO.setDescription(product.getProductDescription());
        requestDTO.setCategory(product.getCategory());
        requestDTO.setImage(product.getProductImage());
        FakeStoreCreateProductResponseDTO responseDTO = restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                requestDTO,
                FakeStoreCreateProductResponseDTO.class
                );

        Product newProduct = new Product();
        assert responseDTO != null;
        newProduct.setId(responseDTO.getId());
        newProduct.setProductTitle(responseDTO.getTitle());
        newProduct.setProductPrice(responseDTO.getPrice());
        newProduct.setProductDescription(responseDTO.getDescription());
        newProduct.setCategory(responseDTO.getCategory());
        newProduct.setProductImage(responseDTO.getImage());
        return newProduct;
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreGetProductResponseDTO [] responseArray = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreGetProductResponseDTO[].class
        );

        if(responseArray == null) {
            throw new RuntimeException("Failed to fetch products from Fake Store API");
        }

        List<FakeStoreGetProductResponseDTO> responseDTOList = Arrays.asList(responseArray);
        List<Product> products = new ArrayList<>();
        for(FakeStoreGetProductResponseDTO fakeStoreGetResponseDTO : responseDTOList) {
            products.add(FakeStoreGetProductResponseDTO.toProduct(fakeStoreGetResponseDTO));
        }

        return products;

    }

    @Override
    public Product updateProduct(Long id , Product product) {
        FakeStoreCreateProductResponseDTO responseDTO = restTemplate.patchForObject(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreCreateProductRequestDTO.fromProduct(product),
                FakeStoreCreateProductResponseDTO.class
        );

        return FakeStoreCreateProductResponseDTO.toProduct(responseDTO);

    }


    @Override
    public Product getSingleProduct(Long id) throws ProductNotFoundException {
        // First we will check in the redis cache if the responseProduct is present or not
        Product product = (Product) redisTemplate.opsForHash().get("Products","PRODUCT_" + id);
        if(product != null){
            return product;
        }

        // If the responseProduct is not present in the redis cache then we will call the Fake Store API to get the responseProduct

        FakeStoreGetProductResponseDTO responseDTO = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreGetProductResponseDTO.class
        );

        if(responseDTO == null) {
            throw new ProductNotFoundException(String.valueOf(id));
        }

        Product responseProduct = FakeStoreGetProductResponseDTO.toProduct(responseDTO);

        redisTemplate.opsForHash().put("Products","PRODUCT_" + id, responseProduct);

        return responseProduct;
    }

    @Override
    public void deleteProduct(Long id) {
        restTemplate.delete(
                "https://fakestoreapi.com/products/" + id
        );


    }
}

/**
 * This is the FakeApiProductService class which implements the ProductService interface.
 * It uses the RestTemplate to make HTTP requests to the Fake Store API.
 * It is used to create a new product by sending a POST request to the Fake Store API.
 * It maps the response from the Fake Store API to our internal Product model.
 * This service is used when we want to interact with the Fake Store API to create products.
 *
 *  restTemplate.getForObject(
 *                 "https://fakestoreapi.com/products",
 *                 List<FakeStoreCreateProductResponseDTO>.class
 *         )
 *
 * why we are getting List<FakeStoreGetProductResponseDTO>.class?
 * Here is the **same enhanced explanation**, with **every word preserved** exactly as requested — just replacing `List<FakeStore>` with `List<FakeStoreCreateProductResponseDTO>` throughout:
 *
 * ---
 *
 * ## ❓ Why does this problem occur when using `List<FakeStoreGetProductResponseDTO>.class`?
 *
 * > 🔍 **Root Cause**: **Type Erasure** — a design decision in Java’s generics system.
 *
 * ---
 *
 * ### 🔬 Step-by-Step Technical Breakdown
 *
 * #### ✅ 1. **Generics in Java are Compile-Time Only**
 *
 * * When you write:
 *
 *   ```java
 *   List<FakeStoreGetProductResponseDTO>
 *   ```
 *
 *   You're telling the **compiler**: “This list will only hold `FakeStoreGetProductResponseDTO` objects.”
 * * The compiler enforces this with **type checks**:
 *
 *   ```java
 *   list.add(new FakeStoreGetProductResponseDTO);        // ✅ OK
 *   list.add(new String("Hello"));                           // ❌ Compile-time error
 *   ```
 *
 * #### ⚠️ 2. **But… at Runtime, This Information Is Gone**
 *
 * * Due to **type erasure**, Java removes all generic type information **during compilation**.
 * * The generic `List<FakeStoreGetProductResponseDTO>` becomes just:
 *
 *   ```java
 *   List
 *   ```
 *
 *   This is called a **raw type**.
 *
 * > 🔥 So `List<FakeStoreGetProductResponseDTO>` and `List<String>` are both treated as just `List` at runtime. The JVM cannot tell the difference.
 *
 * ---
 *
 * ### 🔄 Why Did Java Do This?
 *
 * Java generics were added **after Java 1.4**, but JVM was already running millions of applications. Instead of rewriting the JVM, Java added **generics only in the compiler layer**, not in the runtime.
 *
 * That’s why the **JVM never knows the actual type parameter**.
 *
 * > ✅ **Backwards Compatibility**
 * > ❌ **No Runtime Type Awareness**
 *
 * ---
 *
 * ### ❌ So Why Can’t We Write `List<FakeStoreGetProductResponseDTO>.class`?
 *
 * Because the JVM **doesn’t know what `FakeStoreGetProductResponseDTO` is inside the List**, there’s no `.class` object for `List<FakeStoreCreateProductResponseDTO>` — it simply doesn’t exist at runtime.
 *
 * Only this exists:
 *
 * ```java
 * List.class
 * ```
 *
 * So if you try:
 *
 * ```java
 * List<FakeStoreGetProductResponseDTO>.class
 * ```
 *
 * You get a **compile-time error** — because the compiler knows this type won't exist at runtime.
 *
 * ---
 *
 * ### 📦 Why Is This a Problem When Calling APIs (like with RestTemplate)?
 *
 * When you get a JSON response like:
 *
 * ```json
 * [
 *   { "id": 1, "title": "Product 1" },
 *   { "id": 2, "title": "Product 2" }
 * ]
 * ```
 *
 * You want to map it to:
 *
 * ```java
 * List<FakeStoreGetProductResponseDTO>
 * ```
 *
 * But since the runtime doesn't know it's a `List<FakeStoreGetProductResponseDTO>`, it treats it as a raw `List<Object>`. As a result:
 *
 * * Jackson can't deserialize correctly.
 * * You'll either get:
 *
 *   * A `List` of `LinkedHashMap`s.
 *   * Or a conversion error.
 *
 * ---
 *
 * ### ✅ How Do We Solve This?
 *
 * We pass full type info using an anonymous class:
 *
 * ```java
 * new ParameterizedTypeReference<List<FakeStoreGetProductResponseDTO>>() {}
 * ```
 *
 * This is a hack that **preserves the generic type via anonymous class inheritance**, so that libraries like Jackson can access the actual type using reflection.
 *
 * ---
 *
 * ## 📌 Summary in Simple + Technical Terms
 *
 * | Concept        | Explanation                                                                                                            |
 * | -------------- | ---------------------------------------------------------------------------------------------------------------------- |
 * | Generics       | Exist only at compile-time for type safety.                                                                            |
 * | Type Erasure   | JVM erases generic types during compilation — only raw types exist at runtime.                                         |
 * | Problem        | `List<FakeStoreGetProductResponseDTO>.class` doesn’t exist — JVM only knows `List.class`.                           |
 * | Why This Fails | Jackson (or any deserializer) doesn't know what type to map the JSON array to.                                         |
 * | Solution       | Use `ParameterizedTypeReference<List<FakeStoreGetProductResponseDTO>>() {}` to retain type info through reflection. |
 *
 *
 */
