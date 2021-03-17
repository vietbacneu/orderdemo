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
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Page<OrderProductDTO> getAll(Pageable pageable) {
        Page<OrderProduct> orderProducts = orderProductRepository.findAll(pageable);
        return new PageImpl<>(orderMapper.toOrderDTOList(orderProducts.getContent()), pageable, orderProducts.getTotalElements());
    }

    @Override
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
    public void updateOrder(OrderProductDTO orderProductDTO, Long id) throws NotFoundException {
        Optional<OrderProduct> orderProduct1 = orderProductRepository.findById(id);
        if (!orderProduct1.isPresent()) {
            throw new NotFoundException("không tìm thấy");
        }
        OrderProduct orderProduct = orderMapper.toOrderEntity(orderProductDTO);
        orderProduct.setCreatedBy(orderProduct1.get().getCreatedBy());
        orderProduct.setCreatedTime(orderProduct1.get().getCreatedTime());
        orderProduct.setId(id);
        List<OrderDetail> orderDetails = new ArrayList<>();
        List<OrderDetailDTO> orderDetailDTOList = orderProductDTO.getOrderDetailDTOList();
        for (OrderDetailDTO orderDetailDTO : orderDetailDTOList) {
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
