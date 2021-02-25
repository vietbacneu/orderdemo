package com.example.orderdemo.service;

import com.example.orderdemo.dto.ProductDTO;
import com.example.orderdemo.entity.Product;
import com.example.orderdemo.mapper.ProductMapper;
import com.example.orderdemo.repository.ProductCriteriaJPARepoImpl;
import com.example.orderdemo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceCriteria {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductRepository productRepository;

    public List<ProductDTO> singleOrMultipleExpression() {
        List<Product> products = productRepository.singleOrMultipleExpression();
        System.out.println(products.size());
        List<ProductDTO> productDTOS = new ArrayList<>();
        products.forEach(product -> {
            productDTOS.add(productMapper.toProductDTO(product));
        });
        return productDTOS;
    }

//    public List<ProductDTO> whereByNameOrId(String name, Long id) {
//        List<Product> products = productRepository.whereByNameOrId(name, id);

    //        List<ProductDTO> productDTOS = new ArrayList<>();
//        products.forEach(product -> {
//            productDTOS.add(productMapper.toProductDTO(product));
//        });
//        return productDTOS;
//    }
    public List<ProductDTO> whereByNameOrId(String name, Long id) {
        Specification specification = Specification.where(ProductCriteriaJPARepoImpl.byName(name)).or(ProductCriteriaJPARepoImpl.byId(id));
        List<Product> products = productRepository.findAll(specification);
        List<ProductDTO> productDTOS = new ArrayList<>();
        products.forEach(product -> {
            productDTOS.add(productMapper.toProductDTO(product));
        });
        return productDTOS;
    }


    public List<ProductDTO> groupByAndHavingAndOrder() {
        List<Product> products = productRepository.groupByAndHavingAndOrder();
        System.out.println(products.size());
        List<ProductDTO> productDTOS = new ArrayList<>();
        products.forEach(product -> {
            productDTOS.add(productMapper.toProductDTO(product));
        });
        return productDTOS;
    }

}
