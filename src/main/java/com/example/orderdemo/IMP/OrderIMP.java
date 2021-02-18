package com.example.orderdemo.IMP;

import com.example.orderdemo.dto.OrderProductDTO;

import java.util.List;

public interface OrderIMP {
    List<OrderProductDTO> getAll();
    void addOrder(OrderProductDTO productDTO);
    void updateOrder(OrderProductDTO orderProductDTO, Long id);
    void deleteOrder(Long id);
}
