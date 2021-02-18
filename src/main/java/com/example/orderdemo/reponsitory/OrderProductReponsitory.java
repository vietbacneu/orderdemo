package com.example.orderdemo.reponsitory;

import com.example.orderdemo.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductReponsitory extends JpaRepository<OrderProduct,Long> {
}
