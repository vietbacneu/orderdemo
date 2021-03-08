package com.example.orderdemo.service;

import com.example.orderdemo.dto.ProCatDTO;
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

import java.util.List;

@Service
public class ProductServiceCriteria {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductRepository productRepository;

    public List<ProductDTO> singleOrMultipleExpression() {
        List<Product> products = productRepository.singleOrMultipleExpression();
        return productMapper.toDTOList(products);
    }

    public List<ProductDTO> getProductDTO() {
        return productRepository.getProductDTO();
    }

    public List<Object[]> getProductDTO1() {
        return productRepository.getProductDTO1();
    }

    public Page<ProCatDTO> getCriteriaJoin(SearchRequest searchRequest, Pageable pageable) {
        return productRepository.getCriteriaJoin(searchRequest, pageable);
    }

    public Page<ProductDTO> getSpec(SearchRequest searchRequest, Pageable pageable) {
        Specification specification = Specification.where(ProductCriteriaJPARepoImpl.productSpecification(searchRequest));
        Page<Product> products = productRepository.findAll(specification, pageable);
        List<ProductDTO> productDTOS = productMapper.toDTOList(products.getContent());
        return new PageImpl<>(productDTOS, pageable, products.getTotalElements());
    }


    public List<ProductDTO> groupByAndHavingAndOrder() {
        List<Product> products = productRepository.groupByAndHavingAndOrder();
        return productMapper.toDTOList(products);
    }

}
