package com.compassouol.productms.builder;

import com.compassouol.productms.model.Product;
import com.compassouol.productms.model.dto.ProductDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductBuilder {

    public Product productDtoToEntity(ProductDTO productDTO) {
        return Product.builder().name(productDTO.getName()).price(productDTO.getPrice()).description(productDTO.getDescription()).build();
    }

}
