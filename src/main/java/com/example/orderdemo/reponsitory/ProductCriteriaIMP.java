package com.example.orderdemo.reponsitory;

import com.example.orderdemo.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCriteriaIMP {
    List<Product> singleOrMultipleExpression();

    List<Product> whereByNameOrId(String name, Long id);

    List<Product> groupByAndHavingAndOrder();
}
