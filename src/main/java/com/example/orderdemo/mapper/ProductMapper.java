package com.example.orderdemo.mapper;

import com.example.orderdemo.dto.ProductDTO;

import com.example.orderdemo.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(uses = {ProductDetailMapper.class}, componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "productDetailList", target = "productDetailDTO")
    @Mapping(source = "id", target = "idProduct")
    @Mapping(source = "category.id", target = "idCategory")
    ProductDTO toProductDTO(Product product);

    List<Product> toEntityList(List<ProductDTO> productDTOS);

    void update(ProductDTO productDTO, @MappingTarget Product product);

    @Mapping(source = "productDetailDTO", target = "productDetailList")
    @Mapping(source = "idProduct", target = "id")
    @Mapping(source = "idCategory", target = "category.id")
    Product toProductEntity(ProductDTO productDTO);

    List<ProductDTO> toDTOList(List<Product> products);
}
