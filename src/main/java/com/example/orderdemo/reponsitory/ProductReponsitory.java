package com.example.orderdemo.reponsitory;//package com.example.orderexample.reponsitory;

import com.example.orderdemo.dto.ProductDTO;
import com.example.orderdemo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductReponsitory extends JpaRepository<Product,Long> {



}
