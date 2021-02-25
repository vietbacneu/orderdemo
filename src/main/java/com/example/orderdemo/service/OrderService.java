package com.example.orderdemo.service;

import com.example.orderdemo.IMP.OrderIMP;
import com.example.orderdemo.dto.OrderDetailDTO;
import com.example.orderdemo.dto.OrderProductDTO;
import com.example.orderdemo.entity.OrderDetail;
import com.example.orderdemo.entity.OrderProduct;
import com.example.orderdemo.entity.ProductDetail;
import com.example.orderdemo.mapper.OrderDetailMapper;
import com.example.orderdemo.mapper.OrderMapper;
import com.example.orderdemo.repository.OrderDetailRepository;
import com.example.orderdemo.repository.OrderProductRepository;
import com.example.orderdemo.repository.ProductDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService implements OrderIMP {
    @Autowired
    OrderProductRepository orderProductRepository;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderDetailMapper orderDetailMapper;
    @Autowired
    ProductDetailRepository productDetailRepository;
    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Override
    public List<OrderProductDTO> getAll() {
        List<OrderProduct> orderProducts = orderProductRepository.findAll();
        List<OrderProductDTO> orderProductDTOS = new ArrayList<>();
        orderProducts.forEach(orderProduct -> {
            orderProductDTOS.add(orderMapper.toOrderDTO(orderProduct));
        });
        return orderProductDTOS;
    }

    @Override
    @Transactional
    public void addOrder(OrderProductDTO orderProductDTO) {
        OrderProduct orderProduct = orderMapper.toOrderEntity(orderProductDTO);
        List<OrderDetailDTO> orderDetailDTOList = orderProductDTO.getOrderDetailDTOList();
        List<OrderDetail> orderDetails = new ArrayList<>();
        orderDetailDTOList.forEach(orderDetailDTO -> {
            OrderDetail orderDetail = orderDetailMapper.toOrderDetailEntity(orderDetailDTO);
            orderDetail.setOrder(orderProduct);
            orderDetails.add(orderDetail);
        });
        orderProduct.setOrderDetailList(orderDetails);
        orderProductRepository.save(orderProduct);
    }

    @Override
    public void updateOrder(OrderProductDTO orderProductDTO, Long id) {
        OrderProduct orderProduct = orderMapper.toOrderEntity(orderProductDTO);
        orderProduct.setId(id);
        List<OrderDetail> orderDetails = new ArrayList<>();
        List<OrderDetailDTO> orderDetailDTOList = orderProductDTO.getOrderDetailDTOList();
        for (int i = 0; i < orderDetailDTOList.size(); i++) {
            OrderDetailDTO orderDetailDTO = orderDetailDTOList.get(i);
            OrderDetail orderDetail = orderDetailMapper.toOrderDetailEntity(orderDetailDTO);
            ProductDetail productDetail = productDetailRepository.getOne(orderDetailDTO.getIdProductDetail());
            orderDetail.setProductDetail(productDetail);
            orderDetail.setOrder(orderProduct);
            orderDetails.add(orderDetail);
        }
        orderProduct.setOrderDetailList(orderDetails);
        orderProductRepository.save(orderProduct);


    }

    @Override
    public void deleteOrder(Long id) {
        orderProductRepository.deleteById(id);
    }
}
