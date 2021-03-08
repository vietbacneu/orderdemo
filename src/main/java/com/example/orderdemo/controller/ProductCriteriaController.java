package com.example.orderdemo.controller;

import com.example.orderdemo.dto.ProCatDTO;
import com.example.orderdemo.dto.ProductDTO;
import com.example.orderdemo.request.SearchRequest;
import com.example.orderdemo.service.ProductServiceCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/criteria")
public class ProductCriteriaController {
    @Autowired
    ProductServiceCriteria productServiceCriteria;

    @GetMapping
    public List<ProductDTO> singleOrMultipleExpression() {
        return productServiceCriteria.singleOrMultipleExpression();
    }

    @GetMapping("/test")
    public Page<ProCatDTO> getCriteriaJoin(@ModelAttribute SearchRequest searchRequest,
                                           @PageableDefault Pageable pageable) {
        return productServiceCriteria.getCriteriaJoin(searchRequest, pageable);
    }

    @GetMapping("/test1")
    public List<ProductDTO> getProductDTO() {
        return productServiceCriteria.getProductDTO();
    }

    @GetMapping("/test2")
    public List<Object[]> getProductDTO1() {
        return productServiceCriteria.getProductDTO1();
    }

    @GetMapping("/test3")
    public Page<ProductDTO> getSpec(@ModelAttribute SearchRequest searchRequest, @PageableDefault Pageable pageable) {
        return productServiceCriteria.getSpec(searchRequest, pageable);
    }

    @GetMapping("group")
    public List<ProductDTO> groupByAndHavingAndOrder() {
        return productServiceCriteria.groupByAndHavingAndOrder();
    }
}
