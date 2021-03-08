package com.example.orderdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProSizeDTO {
    private Long idProduct;
    private String nameProduct;
    private Long priceProduct;
    private Long quantityPro;
    private Long idCategory;
    private String nameCategory;

    public ProSizeDTO(Long idProduct, String nameProduct, Long idCategory, String nameCategory) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.idCategory = idCategory;
        this.nameCategory = nameCategory;
    }
}
