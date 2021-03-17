package com.example.orderdemo.service;

import com.example.orderdemo.IMP.ProductIMP;
import com.example.orderdemo.dto.ProductDTO;
import com.example.orderdemo.dto.ProductDetailDTO;
import com.example.orderdemo.entity.Product;
import com.example.orderdemo.entity.ProductDetail;
import com.example.orderdemo.mapper.ProductDetailMapper;
import com.example.orderdemo.mapper.ProductMapper;
import com.example.orderdemo.repository.ProductDetailRepository;
import com.example.orderdemo.repository.ProductRepository;
import com.example.orderdemo.request.SearchRequest;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements ProductIMP {
    @Autowired
    ProductDetailMapper productDetailMapper;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductDetailRepository productDetailRepository;

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

    @Transactional
    public void updateProduct(ProductDTO productDTO, Long id) throws NotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()) {
            throw new NotFoundException("Không có sp nào");
        }
        productMapper.update(productDTO, product.get());
        List<ProductDetail> productDetailList = new ArrayList<>();
        List<ProductDetailDTO> productDetailDTOS = productDTO.getProductDetailDTO();
        productDetailDTOS.forEach(productDetailDTO -> {
            Optional<ProductDetail> productDetail = productDetailRepository.findById(productDetailDTO.getId());
            if (productDetail.isPresent()) {
                productDetailMapper.update(productDetailDTO, productDetail.get());
                productDetailList.add(productDetail.get());
            }
        });
        product.get().setProductDetailList(productDetailList);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
