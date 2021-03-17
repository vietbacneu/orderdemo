package com.example.orderdemo.mapper;

import com.example.orderdemo.dto.ProductDetailDTO;
import com.example.orderdemo.entity.ProductDetail;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductDetailMapper {
    @Mapping(source = "size.id", target = "idSize")
    @Mapping(source = "product.id", target = "idProduct")
    ProductDetailDTO toProductDetailDTO(ProductDetail productDetail);

    @InheritConfiguration
    void update(ProductDetailDTO productDetailDTO, @MappingTarget ProductDetail productDetail);

    @Mapping(source = "idSize", target = "size.id")
    @Mapping(source = "idProduct", target = "product.id")
    ProductDetail toProductDetailEntity(ProductDetailDTO productDetailDTO);
}
