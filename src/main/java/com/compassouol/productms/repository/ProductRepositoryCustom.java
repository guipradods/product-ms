package com.compassouol.productms.repository;

import com.compassouol.productms.model.Product;

import java.util.List;

public interface ProductRepositoryCustom {

    List<Product> findProductsByFilter(String q, Double min_price, Double max_price);

}
