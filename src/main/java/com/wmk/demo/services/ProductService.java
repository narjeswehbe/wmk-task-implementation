package com.wmk.demo.services;

import com.wmk.demo.entity.Product;
import com.wmk.demo.repository.ProductRepository;

import java.util.List;

public interface ProductService {
    Product create(Product p );
    Product update(Product p);
    void delete(Long id);
    Product get(Long id);
    List<Product> getAllByCategory(Long id); //category id




}
