package com.example.orderdemo.repository;

import com.example.orderdemo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

//    @Query("SELECT new com.example.orderdemo.dto.CategoryDTO(u.id, u.name) FROM category u WHERE u.id = :id")
//    List<CategoryDTO> findCategoryById(@Param("id") Long id);
}
