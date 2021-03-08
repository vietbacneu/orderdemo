package com.example.orderdemo.repository;

import com.example.orderdemo.dto.ProSizeDTO;
import com.example.orderdemo.dto.ProductDTO;
import com.example.orderdemo.entity.Product;
import com.example.orderdemo.request.SearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCustomRepo {
    List<Product> singleOrMultipleExpression();

    List<Product> whereByNameOrId(String name, Long id);

    List<Product> groupByAndHavingAndOrder();
    List<ProductDTO> getProductDTO();
    List<Object[]> getProductDTO1();
    Page<ProSizeDTO> getCriteriaJoin(SearchRequest request, Pageable pageable);
}
