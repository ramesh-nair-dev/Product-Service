package com.example.productservice.service.specification;

import com.example.productservice.dtos.search.FilterDTO;
import com.example.productservice.models.Product;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import java.util.List;

public class ProductSpecification {

    public static Specification<Product> build(String query, List<FilterDTO> filters) {
        return (root, cq, cb) -> {


            Predicate predicate = cb.like(cb.lower(root.get("productTitle")), "%" + query.toLowerCase() + "%");

            if (filters != null) {
                for (FilterDTO filter : filters) {
                    String key = filter.getKey();
                    List<String> values = filter.getValues();
                    if (values != null && !values.isEmpty()) {
                        Path<String> path = root.get(key); // entity field must match key
                        predicate = cb.and(predicate, path.in(values));
                    }
                }
            }

            return predicate;
        };
    }
}
