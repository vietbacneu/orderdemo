package com.example.orderdemo.mapper;

import com.example.orderdemo.dto.ProductDetailDTO;
import com.example.orderdemo.entity.ProductDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductDetailMapper {
    @Mapping(source = "size.id", target = "idSize")
    @Mapping(source = "product.id", target = "idProduct")
    @Mapping(source = "id", target = "id")
    ProductDetailDTO toProductDetaiDTO(ProductDetail productDetail);

    @Mapping(source = "idSize", target = "size.id")
    @Mapping(source = "idProduct", target = "product.id")
    @Mapping(source = "id", target = "id")
    ProductDetail toProductDetailEntity(ProductDetailDTO productDetailDTO);
}
