package com.example.orderdemo.service;

import com.example.orderdemo.IMP.OrderIMP;
import com.example.orderdemo.dto.OrderDetailDTO;
import com.example.orderdemo.dto.OrderProductDTO;
import com.example.orderdemo.dto.ProductDetailDTO;
import com.example.orderdemo.entity.OrderDetail;
import com.example.orderdemo.entity.OrderProduct;
import com.example.orderdemo.entity.Product;
import com.example.orderdemo.entity.ProductDetail;
import com.example.orderdemo.mapper.OrderDetailMapper;
import com.example.orderdemo.mapper.OrderMapper;
import com.example.orderdemo.reponsitory.OrderDetailReponsitory;
import com.example.orderdemo.reponsitory.OrderProductReponsitory;
import com.example.orderdemo.reponsitory.ProductDetailReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService implements OrderIMP {
    @Autowired
    OrderProductReponsitory orderProductReponsitory;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderDetailMapper orderDetailMapper;
    @Autowired
    ProductDetailReponsitory productDetailReponsitory;
    @Autowired
    OrderDetailReponsitory orderDetailReponsitory;
    @Override
    public List<OrderProductDTO> getAll() {
        List<OrderProduct> orderProducts = orderProductReponsitory.findAll();
        List<OrderProductDTO> orderProductDTOS = new ArrayList<>();
        orderProducts.forEach(orderProduct -> {
            orderProductDTOS.add(orderMapper.toOrderDTO(orderProduct));
        });
        return orderProductDTOS;
    }

    @Override
    public void addOrder(OrderProductDTO orderProductDTO) {
        OrderProduct orderProduct = orderMapper.toOrderEntity(orderProductDTO);
        List<OrderDetailDTO> orderDetailDTOList = orderProductDTO.getOrderDetailDTOList();
        List<OrderDetail> orderDetails = new ArrayList<>();
        orderDetailDTOList.forEach(orderDetailDTO -> {
            OrderDetail orderDetail = orderDetailMapper.toOrderDetailEntity(orderDetailDTO);
            ProductDetail productDetail = productDetailReponsitory.getOne(orderDetailDTO.getIdProductDetail());
            orderDetail.setProductDetail(productDetail);
            orderDetail.setOrder(orderProduct);
            orderDetails.add(orderDetail);
        });
        orderProduct.setOrderDetailList(orderDetails);
        orderProductReponsitory.save(orderProduct);
    }

    @Override
    public void updateOrder(OrderProductDTO orderProductDTO, Long id) {
        OrderProduct orderProduct = orderMapper.toOrderEntity(orderProductDTO);
        orderProduct.setId(id);
        List<OrderDetail> orderDetails = new ArrayList<>();
        List<OrderDetailDTO> orderDetailDTOList = orderProductDTO.getOrderDetailDTOList();
        for (int i = 0; i <orderDetailDTOList.size() ; i++) {
            OrderDetailDTO orderDetailDTO = orderDetailDTOList.get(i);
            OrderDetail orderDetail = orderDetailMapper.toOrderDetailEntity(orderDetailDTO);
            ProductDetail productDetail = productDetailReponsitory.getOne(orderDetailDTO.getIdProductDetail());
            orderDetail.setProductDetail(productDetail);
            orderDetail.setOrder(orderProduct);
            orderDetails.add(orderDetail);
        }
        orderProduct.setOrderDetailList(orderDetails);
        orderProductReponsitory.save(orderProduct);


    }

    @Override
    public void deleteOrder(Long id) {
        orderProductReponsitory.deleteById(id);
    }
}
