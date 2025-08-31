package com.example.productservice.service.sortingService;

public class SorterFactory {
    public static Sorter getSorter(String type) {
        return switch (type) {
            case "PRICE_ASC" -> new PRICE_ASC();
            case "PRICE_DESC" -> new PRICE_DESC();
            default -> throw new IllegalArgumentException("Unknown sorter key");
        };
    }
}
