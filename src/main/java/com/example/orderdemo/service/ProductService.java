package com.example.orderdemo.service;

import com.example.orderdemo.IMP.ProductIMP;
import com.example.orderdemo.dto.ProductDTO;
import com.example.orderdemo.dto.ProductDetailDTO;
import com.example.orderdemo.entity.Product;
import com.example.orderdemo.entity.ProductDetail;
import com.example.orderdemo.mapper.ProductDetailMapper;
import com.example.orderdemo.mapper.ProductMapper;
import com.example.orderdemo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
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


    public List<ProductDTO> getAll() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOS = new ArrayList<>();
        products.forEach(product -> {
            productDTOS.add(productMapper.toProductDTO(product));
        });
        return productDTOS;
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
