package com.example.productservice.dtos.search;

import com.example.productservice.dtos.ResponseStatus;
import com.example.productservice.dtos.product.GetProductDTO;
import com.example.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
public class SearchResponseDTO {
    private Page<GetProductDTO> productsPage;
    private ResponseStatus responseStatus;

    public static SearchResponseDTO fromProductsPage(Page<Product> productsPage) {
        SearchResponseDTO dto = new SearchResponseDTO();
        dto.setProductsPage(productsPage.map(GetProductDTO::fromProduct));
        dto.setResponseStatus(ResponseStatus.Success);
        return dto;
    }
}
