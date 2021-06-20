package com.compassouol.productms.controller;

import com.compassouol.productms.model.dto.ProductDTO;
import com.compassouol.productms.service.ProductService;
import com.compassouol.productms.util.ErrorMessageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping("/products")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ProductController {

    private final ProductService productService;

    @PostMapping()
    public ResponseEntity createProduct(@Valid @RequestBody ProductDTO productDTO) {

        try {
            var product = productService.registerProduct(productDTO);
            return new ResponseEntity(product, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorMessageUtil(Response.SC_BAD_REQUEST, e.getMessage()));
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {

        var product = productService.findProductById(id);
        if (!product.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        try {
            var productUpdated = productService.updateProduct(product, productDTO);
            return ResponseEntity.ok().body(productUpdated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorMessageUtil(Response.SC_BAD_REQUEST, e.getMessage()));
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity findProductById(@PathVariable Long id) {

        var product = productService.findProductById(id);
        if (!product.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        try {
            return ResponseEntity.ok().body(product.get());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping()
    public ResponseEntity findAllProducts() {

        try {
            var products = productService.findAllProducts();
            return ResponseEntity.ok().body(products);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/search")
    public ResponseEntity findFilteredProducts(@RequestParam(required = false) String q,
                                               @RequestParam(required = false) Double min_price,
                                               @RequestParam(required = false) Double max_price) {

        var products = productService.findFilteredProducts(q, min_price, max_price);

        try {
            return ResponseEntity.ok().body(products);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id) {

        var product = productService.findProductById(id);
        if (!product.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        try {
            productService.deleteProduct(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

}
