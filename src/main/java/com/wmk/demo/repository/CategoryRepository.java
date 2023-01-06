package com.wmk.demo.repository;

import com.wmk.demo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository  extends JpaRepository< Category , Long> {
}
