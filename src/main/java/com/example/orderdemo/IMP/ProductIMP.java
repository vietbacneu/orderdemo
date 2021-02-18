package com.example.orderdemo.IMP;

import com.example.orderdemo.dto.ProductDTO;

import java.util.List;

public interface ProductIMP {
    List<ProductDTO> getAll();
    void add(ProductDTO productDTO);
    void updateProduct(ProductDTO productDTO, Long id);
    void deleteProduct(Long id);
}
