package com.example.orderdemo.controller;

import com.example.orderdemo.dto.OrderProductDTO;
import com.example.orderdemo.service.OrderService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping
    public Page<OrderProductDTO> getAllOrder(@PageableDefault Pageable pageable) {
        return orderService.getAll(pageable);
    }

    @PostMapping
    public void addOrderProduct(@RequestBody OrderProductDTO orderProductDTO) {
        orderService.addOrder(orderProductDTO);
    }

    @PutMapping("/{id}")
    public void updateOrder(@RequestBody OrderProductDTO orderProductDTO, @PathVariable Long id) throws NotFoundException {
        orderService.updateOrder(orderProductDTO, id);
        System.out.println("hello");
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
}
