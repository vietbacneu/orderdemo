package com.example.orderdemo.reponsitory;

import com.example.orderdemo.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailReponsitory extends JpaRepository<ProductDetail, Long> {
}
