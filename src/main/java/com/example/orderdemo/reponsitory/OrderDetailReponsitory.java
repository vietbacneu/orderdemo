package com.example.orderdemo.reponsitory;

import com.example.orderdemo.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailReponsitory extends JpaRepository<OrderDetail,Long> {
}
