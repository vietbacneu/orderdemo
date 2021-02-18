package com.example.orderdemo.reponsitory;//package com.example.orderexample.reponsitory;

import com.example.orderdemo.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductReponsitory extends JpaRepository<Product,Long> {
}
