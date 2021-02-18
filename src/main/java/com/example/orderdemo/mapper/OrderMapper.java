package com.example.orderdemo.mapper;

import com.example.orderdemo.dto.OrderProductDTO;
import com.example.orderdemo.entity.OrderProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {OrderDetailMapper.class},componentModel = "spring")
public interface OrderMapper {
    @Mapping(source = "customer.id", target = "idCustomer")
    @Mapping(source = "orderDetailList", target = "orderDetailDTOList")
    OrderProductDTO toOrderDTO(OrderProduct orderProduct);
    @Mapping(source = "idCustomer", target = "customer.id")
    @Mapping(source = "orderDetailDTOList", target = "orderDetailList")
    OrderProduct toOrderEntity(OrderProductDTO orderProductDTO);
}
