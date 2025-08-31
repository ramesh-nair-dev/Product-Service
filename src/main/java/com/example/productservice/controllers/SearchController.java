package com.example.productservice.controllers;

import com.example.productservice.dtos.search.FilterDTO;
import com.example.productservice.dtos.search.SearchResponseDTO;
import com.example.productservice.dtos.search.SortingCriteria;
import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Product;
import com.example.productservice.service.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")

public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/")
    public SearchResponseDTO search(
            /**
             * Flow of search request:
             * 1. User sends a search request with query, filters, and sorting criteria.
             * 2. For example user want to search for iphone 16 , with some filter like
             *         a. price : 1000 - 2000 , ram : 8GB , storage : 128GB
             *         b. sorting criteria : price low to high
             *         c. query : iphone 16
             * 3. We have SearchResponseDTO which contains list product what match the search criteria
             * 4. We have filterDto like:
             *         a. format key : value1 , value2 , value3
             *         b. example : price : 1000 , 2000
             *         c. example : ram : 8GB , 16GB
             *         d. example : storage : 128GB , 256GB
             *         e. example : brand : apple , samsung
             *         f. example : category : electronics , fashion
             * 5. We have sorting criteria enum like:
             *         a. PRICE_LOW_TO_HIGH
             *         b. PRICE_HIGH_TO_LOW
             *         c. RATING_HIGH_TO_LOW
             *
             * 6. We will pass the query , List<FilterDTO> and sorting criteria to the service layer from controller layer
             *    why list<FilterDTO> because user can apply multiple filters at the same time
             *    for example : price : 1000 - 2000 , ram : 8GB , storage : 128GB
             */


            @RequestParam("query") String query,
            @RequestParam("filter") List<FilterDTO> filter,
            @RequestParam("sortBy")SortingCriteria sortingCriteria,
            @RequestParam("pageNumber") int pageNumber,
            @RequestParam("pageSize") int pageSize
            ) throws ProductNotFoundException
    {

        Page<Product> productPage =  searchService.searchProducts(
                query,
                filter,
                sortingCriteria,
                pageNumber,
                pageSize
        );

        if(productPage.isEmpty()){
            throw new ProductNotFoundException("No products found matching the query: " + query);
        }

        return SearchResponseDTO.fromProductsPage(productPage);
    }
}

