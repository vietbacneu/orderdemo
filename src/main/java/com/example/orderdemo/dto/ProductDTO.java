package com.example.orderdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private Long idProduct;
    @NotNull(message = "{notnull}")
    private String name;
    @NotNull(message = "{notnull}")
    @Min(value = 0, message = "{price.min}")
    private Long price;
    @NotNull(message = "{notnull}")
    @Min(value = 0, message = "{quantity.min}")
    private Long quantity;
    private String description;
    private String image;
    private Long idCategory;
    private List<ProductDetailDTO> productDetailDTO;


    public ProductDTO(Long idProduct, @NotNull String name) {
        this.idProduct = idProduct;
        this.name = name;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }

    public List<ProductDetailDTO> getProductDetailDTO() {
        return productDetailDTO;
    }

    public void setProductDetailDTO(List<ProductDetailDTO> productDetailDTO) {
        this.productDetailDTO = productDetailDTO;
    }
}
