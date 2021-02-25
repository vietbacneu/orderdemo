package com.example.orderdemo.repository;

import com.example.orderdemo.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, ProductCustomRepo, JpaSpecificationExecutor<Product> {

    @Query(value = "SELECT * FROM product p WHERE p.name like %:name% and p.price > :min and p.price < :max ",
            countQuery = "select count(*) from product",
            nativeQuery = true)
    Page<Product> getAll(@Param("name") String name, @Param("min") Long min, @Param("max") Long max, Pageable pageable);


}
