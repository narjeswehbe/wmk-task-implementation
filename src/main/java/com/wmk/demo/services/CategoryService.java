package com.wmk.demo.services;

import com.wmk.demo.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryService {
    Category create(Category c );
    Category update(Category c);
    void delete(Long id);
    Category get(Long id);
    List<Category> getAll();




}
