package com.example.orderdemo.service;

import com.example.orderdemo.dto.ProSizeDTO;
import com.example.orderdemo.dto.ProductDTO;
import com.example.orderdemo.entity.Product;
import com.example.orderdemo.mapper.ProductMapper;
import com.example.orderdemo.repository.ProductCriteriaJPARepoImpl;
import com.example.orderdemo.repository.ProductRepository;
import com.example.orderdemo.request.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

    public List<ProductDTO> getProductDTO() {
        return productRepository.getProductDTO();
    }

    public List<Object[]> getProductDTO1() {
        return productRepository.getProductDTO1();
    }

    public Page<ProSizeDTO> getCriteriaJoin(SearchRequest searchRequest, Pageable pageable) {
        return productRepository.getCriteriaJoin(searchRequest, pageable);
    }

    public Page<ProductDTO> getSpec (String name, Long id, Pageable pageable) {
        Specification specification = Specification.where(ProductCriteriaJPARepoImpl.productSpecification(name, id));
        Page<Product> products = productRepository.findAll(specification, pageable);
        List<ProductDTO> productDTOS = new ArrayList<>();
        products.forEach(product -> {
            productDTOS.add(productMapper.toProductDTO(product));
        });
        return new PageImpl<>(productDTOS,pageable,products.getTotalElements());
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
