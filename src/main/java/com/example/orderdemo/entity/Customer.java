package com.example.orderdemo.entity;

import com.example.orderdemo.validator.validatorDemo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity(name = "customer")
public class Customer extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @validatorDemo
    private String name;


    @OneToMany
    @JoinColumn(name = "customer_id")
    private List<OrderProduct> orderList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<OrderProduct> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderProduct> orderList) {
        this.orderList = orderList;
    }
}
