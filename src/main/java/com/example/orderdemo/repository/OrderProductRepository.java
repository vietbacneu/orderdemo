package com.example.orderdemo.repository;

import com.example.orderdemo.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface OrderProductRepository extends JpaRepository<OrderProduct,Long> {
}
