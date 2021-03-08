package com.example.orderdemo.service;

import com.example.orderdemo.dto.ProductDTO;
import com.example.orderdemo.entity.Product;
import com.example.orderdemo.mapper.ProductMapper;
import com.example.orderdemo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PageAndSortService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductMapper productMapper;

    public Page<ProductDTO> getAllProductByPage(Pageable pageable) {
        Sort sort = Sort.by("price").descending();

//        Sort sort = Sort.by("price").and(Sort.by("quantity")).descending();
        Page<Product> products = productRepository.findAll(pageable);
        List<ProductDTO> productDTOList = productMapper.toDTOList(products.getContent());
        return new PageImpl<>(productDTOList, pageable, products.getTotalElements());
    }

    public Page<ProductDTO> getProductbyName(Long page, String name, String sort, Long min, Long max) {
        Long pageNumber = page == null ? 0 : page;
        String productName = name == null ? "" : name;
        Long minPrice = min == null ? 0 : min;
        Long maxPrice = max == null ? 10000000 : max;
        int size = 2;
        Pageable pageable = null;
        if (sort == null) {
            pageable = PageRequest.of(pageNumber.intValue(), size);
        } else {
            pageable = PageRequest.of(pageNumber.intValue(), size, Sort.by(sort).descending());
        }
        Page<Product> products = productRepository.getAll(productName, minPrice, maxPrice, pageable);
        List<ProductDTO> productDTOList = productMapper.toDTOList(products.getContent());
        return new PageImpl<>(productDTOList, pageable, products.getTotalElements());
    }



}
