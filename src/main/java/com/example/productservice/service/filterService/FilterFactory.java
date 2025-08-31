package com.example.productservice.service.filterService;

public class FilterFactory {
    public static Filter getFilter(String key) {
        return switch (key.toLowerCase()) {
            case "ram" -> new RAMFilter();
            case "brand" -> new BrandFilter();
            default -> throw new IllegalArgumentException("Unknown filter key: " + key);
        };
    }
}

/**
 * We have implemented a FilterFactory class that provides a method to get filter instances based on a key.
 */
