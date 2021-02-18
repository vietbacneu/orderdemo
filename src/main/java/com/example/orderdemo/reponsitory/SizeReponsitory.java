package com.example.orderdemo.reponsitory;


import com.example.orderdemo.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SizeReponsitory extends JpaRepository<Size,Long> {

}
