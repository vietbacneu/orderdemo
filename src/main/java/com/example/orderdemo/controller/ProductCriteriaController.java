package com.example.orderdemo.controller;

import com.example.orderdemo.dto.ProductDTO;
import com.example.orderdemo.reponsitory.ProductReponsitory;
import com.example.orderdemo.service.ProductServiceCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/criteria")
public class ProductCriteriaController {
@Autowired
    ProductServiceCriteria productServiceCriteria;
@Autowired
    ProductReponsitory productReponsitory;
    @GetMapping()
    public List<ProductDTO> singleOrMultipleExpression(){
        return productServiceCriteria.singleOrMultipleExpression();
    }
    @GetMapping("/test")
    public List<ProductDTO> getNameOrId(@RequestParam("name") String name, @RequestParam("id") String id){
        return productServiceCriteria.whereByNameOrId(name,Long.parseLong(id));
    }

    @GetMapping("3")
    public List<ProductDTO> groupByAndHavingAndOrder(){
        return productServiceCriteria.groupByAndHavingAndOrder();
    }
}
