package com.wmk.demo.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name ;
    private String description;
    private BigDecimal price;
    private Long quantity;

    @OneToMany(cascade=CascadeType.ALL)
    private List<Category> categories ;




}

