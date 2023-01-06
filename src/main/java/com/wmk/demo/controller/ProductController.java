package com.wmk.demo.controller;

import com.wmk.demo.entity.Product;
import com.wmk.demo.repository.ProductRepository;
import com.wmk.demo.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
      return productService.get(id);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public Product create(@RequestBody Product p) {
        return productService.create(p);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update")
    public Product update(@RequestBody Product p) {
        return productService.update(p);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }


}
