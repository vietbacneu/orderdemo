package com.example.orderdemo.reponsitory;//package com.example.orderexample.reponsitory;

import com.example.orderdemo.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductReponsitory extends JpaRepository<Product, Long> {
    @Query(value = "SELECT * FROM product p WHERE p.name like %:name% and p.price > :min and p.price < :max", nativeQuery = true)
    Page<Product> getAll(@Param("name") String name, @Param("min") Long min, @Param("max") Long max, Pageable pageable);
}
