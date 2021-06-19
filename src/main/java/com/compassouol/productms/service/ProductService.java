package com.compassouol.productms.service;

import com.compassouol.productms.builder.ProductBuilder;
import com.compassouol.productms.model.Product;
import com.compassouol.productms.model.dto.ProductDTO;
import com.compassouol.productms.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Product> findProductById(Long id) {
        var product = productRepository.findById(id);
        return product;
    }

    public Product updateProduct(Optional<Product> product, ProductDTO productDTO) {

        return product.map(products -> {
            products.setName(productDTO.getName());
            products.setDescription(productDTO.getDescription());
            products.setPrice(productDTO.getPrice());
            Product updated = saveProduct(products);
            return updated;
        }).orElse(product.get());

    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> findAllProducts() {

        var products = productRepository.findAll();
        return products;

    }

}
