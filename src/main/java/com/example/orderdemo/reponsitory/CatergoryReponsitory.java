package com.example.orderdemo.reponsitory;
import com.example.orderdemo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatergoryReponsitory extends JpaRepository<Category, Long> {
}
