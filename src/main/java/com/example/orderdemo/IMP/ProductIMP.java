package com.example.orderdemo.IMP;

import com.example.orderdemo.dto.ProductDTO;
import com.example.orderdemo.request.SearchRequest;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductIMP {
    Page<ProductDTO> getAllorSearch(SearchRequest searchRequest, Pageable pageable);

    void add(ProductDTO productDTO);

    void updateProduct(ProductDTO productDTO, Long id) throws NotFoundException;

    void deleteProduct(Long id);
}
