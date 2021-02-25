package com.example.orderdemo.repository;

import com.example.orderdemo.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCustomRepo {
    List<Product> singleOrMultipleExpression();

    List<Product> whereByNameOrId(String name, Long id);

    List<Product> groupByAndHavingAndOrder();
}
