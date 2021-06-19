package com.compassouol.productms.service;

import com.compassouol.productms.builder.ProductBuilder;
import com.compassouol.productms.model.Product;
import com.compassouol.productms.model.dto.ProductDTO;
import com.compassouol.productms.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductBuilder productBuilder;

    public Product registerProduct(ProductDTO productDTO) {
        var newProduct = productBuilder.productDtoToEntity(productDTO);
        productRepository.save(newProduct);
        return newProduct;
    }
}
