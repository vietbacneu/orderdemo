package com.example.orderdemo.mapper;

import com.example.orderdemo.dto.OrderDetailDTO;
import com.example.orderdemo.entity.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {
    @Mapping(source = "order.id", target = "idOrder")
    @Mapping(source = "productDetail.id", target = "idProductDetail")
    OrderDetailDTO toOrderDetailDTO(OrderDetail orderDetail);
    @Mapping(source = "idOrder", target = "order.id")
    @Mapping(source = "idProductDetail", target = "productDetail.id")
    OrderDetail toOrderDetailEntity(OrderDetailDTO orderDetailDTO);
}
