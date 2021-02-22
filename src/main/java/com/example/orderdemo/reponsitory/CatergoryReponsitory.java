package com.example.orderdemo.reponsitory;

import com.example.orderdemo.dto.CategoryDTO;
import com.example.orderdemo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CatergoryReponsitory extends JpaRepository<Category, Long> {

//    @Query("SELECT new com.example.orderdemo.dto.CategoryDTO(u.id, u.name) FROM category u WHERE u.id = :id")
//    List<CategoryDTO> findCategoryById(@Param("id") Long id);
}
