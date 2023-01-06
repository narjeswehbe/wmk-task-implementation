package com.wmk.demo.services;

import com.wmk.demo.entity.Category;
import com.wmk.demo.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)



class CategoryServiceImplTest {
    @Mock
    CategoryRepository categoryRepository;
    @InjectMocks
    CategoryServiceImpl categoryService;
    Category returnCategory;

    @BeforeEach
    void setUp() {
        returnCategory = new Category();
        returnCategory.setId(1L);
        returnCategory.setName("category");
    }
    @Test
    void create() {
        Category cat = new Category();
        cat.setId(5L);
        cat.setDescription("description");
        cat.setName("my category");

        when(categoryRepository.save(any())).thenReturn(returnCategory);

        Category saved = categoryService.create(cat);

        assertNotNull(saved);
        verify(categoryRepository).save(any());


    }



    @Test
    void delete() {
        categoryService.delete(returnCategory.getId());

        //default is 1 times
        verify(categoryRepository, times(1)).delete(any());
    }


    @Test
    void getAll() {
        Category c1 = new Category();
        c1.setId(1L);
        c1.setName("category");

        Category c2 = new Category();
        c2.setId(2L);
        c2.setName("category");
        List<Category> cats = new ArrayList<>(List.of(new Category[]{c1, c2}));

        when(categoryRepository.findAll()).thenReturn(cats);

        List<Category> categoryList = categoryService.getAll();

        assertNotNull(categoryList);
        assertEquals(2, categoryList.size());
    }
}