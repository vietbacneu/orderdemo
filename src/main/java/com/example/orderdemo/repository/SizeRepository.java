package com.example.orderdemo.repository;


import com.example.orderdemo.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SizeRepository extends JpaRepository<Size,Long> {

}
