package com.example.orderdemo.IMP;

import com.example.orderdemo.dto.OrderProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderIMP {
    Page<OrderProductDTO> getAll(Pageable pageable);

    void addOrder(OrderProductDTO productDTO);

    void updateOrder(OrderProductDTO orderProductDTO, Long id);

    void deleteOrder(Long id);
}
