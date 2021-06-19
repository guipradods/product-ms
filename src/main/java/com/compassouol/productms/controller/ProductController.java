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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
