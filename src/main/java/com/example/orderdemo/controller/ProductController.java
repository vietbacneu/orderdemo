package com.example.orderdemo.controller;

import com.example.orderdemo.dto.ProductDTO;
import com.example.orderdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public List<ProductDTO> getDefault() {
        return productService.getAll();
    }

    @PostMapping()
    public void add(@RequestBody ProductDTO productDTO) {
        productService.add(productDTO);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody ProductDTO productDTO, @PathVariable Long id) {
        productService.updateProduct(productDTO, id);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);

    }
}
