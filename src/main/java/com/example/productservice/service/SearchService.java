package com.example.productservice.service;

import com.example.productservice.dtos.search.FilterDTO;
import com.example.productservice.dtos.search.SortingCriteria;
import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Product;
import com.example.productservice.repository.ProductRepository;
import com.example.productservice.service.filterService.FilterFactory;
import com.example.productservice.service.sortingService.SorterFactory;
import com.example.productservice.service.specification.ProductSpecification;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    private final ProductRepository productRepository;

    public SearchService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> searchProducts(
            String query,
            List<FilterDTO> filterDTOList,
            SortingCriteria sortingCriteria,
            int pageNumber,
            int pageSize
    ) throws ProductNotFoundException

    {
        // 1.Build Specification object
        // Specification is a dynamic "WHERE clause" builder for JPA.
        // It combines search query + any dynamic filters the user applied.
        Specification<Product> spec = ProductSpecification.build(query, filterDTOList);

        // 2.Convert sorting criteria (enum) to JPA Sort object
        // This is how we tell the database "order by price asc/desc or rating desc"
        Sort sort = Sort.unsorted();
        if (sortingCriteria != null) {
            switch (sortingCriteria) {
                case PRICE_ASC -> sort = Sort.by("productPrice").ascending();
                case PRICE_DESC -> sort = Sort.by("productPrice").descending();
                case RATING_DESC -> sort = Sort.by("rating").descending();
            }
        }

        // 3.Build Pageable object
        // Pageable contains page number, page size, and sorting info.
        // This is crucial because Spring Data will pass this to the DB for LIMIT/OFFSET.
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        // 4️⃣ Fetch products from DB
        // This single line does:
        //   a) Filtering (WHERE clause)
        //   b) Sorting (ORDER BY)
        //   c) Pagination (LIMIT/OFFSET)
        // All in the database, which is efficient.
        Page<Product> productPage = productRepository.findAll(spec, pageable);

        // 5️⃣ Defensive check
        // If no products found, throw exception. This is optional, could return empty page instead.
        if (productPage.isEmpty()) {
            throw new ProductNotFoundException("No products found matching the query: " + query);
        }

        return productPage;

    }
}
