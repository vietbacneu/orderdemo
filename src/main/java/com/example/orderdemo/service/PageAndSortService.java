package com.example.orderdemo.service;

import com.example.orderdemo.dto.ProductDTO;
import com.example.orderdemo.entity.Product;
import com.example.orderdemo.mapper.ProductMapper;
import com.example.orderdemo.reponsitory.ProductCriteriaIMP;
import com.example.orderdemo.reponsitory.ProductReponsitory;
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
    ProductReponsitory productReponsitory;

    @Autowired
    ProductMapper productMapper;

    public Page<ProductDTO> getAllProductByPage(Pageable pageable) {
        Page<Product> products = productReponsitory.findAll(pageable);
        List<ProductDTO> productDTOList = productMapper.toEntity(products.getContent());
        return new PageImpl<>(productDTOList, pageable, products.getTotalElements());
    }

    public Page<ProductDTO> getProductbyName(Integer page, String name, String sort, Long min, Long max) {
        int pageNumber = page==null?0:page;
        String sortName = sort==null?"":sort;
        String productName = name==null?"":name;
        Long minPrice = min==null?0:min;
        Long maxPrice = max==null?10000000:max;
        Pageable pageable = PageRequest.of(pageNumber, 10,Sort.by(sortName).descending());
        Page<Product> products = productReponsitory.getAll(productName,minPrice,maxPrice,pageable);
        List<ProductDTO> productDTOList = productMapper.toEntity(products.getContent());
        return new PageImpl<>(productDTOList, pageable, products.getTotalElements());
    }
}
