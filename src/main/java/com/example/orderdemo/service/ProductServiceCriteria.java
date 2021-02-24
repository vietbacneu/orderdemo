package com.example.orderdemo.service;

import com.example.orderdemo.dto.ProductDTO;
import com.example.orderdemo.entity.Product;
import com.example.orderdemo.mapper.ProductDetailMapper;
import com.example.orderdemo.mapper.ProductMapper;
import com.example.orderdemo.reponsitory.ProductCriteriaIMP;
import com.example.orderdemo.reponsitory.ProductReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceCriteria {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductCriteriaIMP productCriteriaIMP;

    public List<ProductDTO> singleOrMultipleExpression() {
        List<Product> products = productCriteriaIMP.singleOrMultipleExpression();
        System.out.println(products.size());
        List<ProductDTO> productDTOS = new ArrayList<>();
        products.forEach(product -> {
            productDTOS.add(productMapper.toProductDTO(product));
        });
        return productDTOS;
    }

    public List<ProductDTO> whereByNameOrId(String name, Long id) {
        List<Product> products = productCriteriaIMP.whereByNameOrId(name, id);
        System.out.println(products.size());
        List<ProductDTO> productDTOS = new ArrayList<>();
        products.forEach(product -> {
            productDTOS.add(productMapper.toProductDTO(product));
        });
        return productDTOS;
    }

    public List<ProductDTO> groupByAndHavingAndOrder() {
        List<Product> products = productCriteriaIMP.groupByAndHavingAndOrder();
        System.out.println(products.size());
        List<ProductDTO> productDTOS = new ArrayList<>();
        products.forEach(product -> {
            productDTOS.add(productMapper.toProductDTO(product));
        });
        return productDTOS;
    }

}
