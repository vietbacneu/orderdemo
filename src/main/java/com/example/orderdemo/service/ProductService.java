package com.example.orderdemo.service;

import com.example.orderdemo.IMP.ProductIMP;
import com.example.orderdemo.dto.ProductDTO;
import com.example.orderdemo.dto.ProductDetailDTO;
import com.example.orderdemo.entity.Product;
import com.example.orderdemo.entity.ProductDetail;
import com.example.orderdemo.mapper.ProductDetailMapper;
import com.example.orderdemo.mapper.ProductMapper;
import com.example.orderdemo.repository.ProductRepository;
import com.example.orderdemo.request.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements ProductIMP {
    @Autowired
    ProductDetailMapper productDetailMapper;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductRepository productRepository;


    public Page<ProductDTO> getAllorSearch(SearchRequest searchRequest, Pageable pageable) {
        String productName = searchRequest.getProName() == null ? "" : searchRequest.getProName();
        Long minPrice = searchRequest.getMin() == null ? 0 : searchRequest.getMin();
        Long maxPrice = searchRequest.getMax() == null ? 10000000 : searchRequest.getMax();
        Page<Product> products = productRepository.getAll(productName, minPrice, maxPrice, pageable);
        List<ProductDTO> productDTOList = productMapper.toDTOList(products.getContent());
        return new PageImpl<>(productDTOList, pageable, products.getTotalElements());
    }

    public void add(ProductDTO productDTO) {
        Product product = productMapper.toProductEntity(productDTO);
        List<ProductDetailDTO> productDetailDTOS = productDTO.getProductDetailDTO();
        List<ProductDetail> productDetailList = new ArrayList<>();
        productDetailDTOS.forEach(p -> {
            ProductDetail productDetail = productDetailMapper.toProductDetailEntity(p);
            productDetail.setProduct(product);
            productDetailList.add(productDetail);
        });
        product.setProductDetailList(productDetailList);
        productRepository.save(product);

    }

    public void updateProduct(ProductDTO productDTO, Long id) {
        Product product = productMapper.toProductEntity(productDTO);
        product.setId(id);
        List<ProductDetail> productDetailList = new ArrayList<>();
        List<ProductDetailDTO> productDetailDTOList = productDTO.getProductDetailDTO();
        for (int i = 0; i < productDetailDTOList.size(); i++) {
            ProductDetailDTO productDetailDTO = productDTO.getProductDetailDTO().get(i);
            ProductDetail productDetail = productDetailMapper.toProductDetailEntity(productDetailDTO);
            productDetail.setProduct(product);
            productDetailList.add(productDetail);
        }
        product.setProductDetailList(productDetailList);
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
