package com.example.orderdemo.dto;

public class OrderDetailDTO {
    private Long id;
    private Long idOrder;
    private Long idProductDetail;
    private Long price;
    private Long quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public Long getIdProductDetail() {
        return idProductDetail;
    }

    public void setIdProductDetail(Long idProductDetail) {
        this.idProductDetail = idProductDetail;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
