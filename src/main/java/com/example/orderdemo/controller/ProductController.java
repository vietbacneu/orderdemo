package com.example.orderdemo.controller;

import com.example.orderdemo.dto.ProductDTO;
import com.example.orderdemo.request.SearchRequest;
import com.example.orderdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;


    @GetMapping
    public Page<ProductDTO> getAll(@ModelAttribute SearchRequest searchRequest, @PageableDefault Pageable pageable) {
        return productService.getAllorSearch(searchRequest, pageable);
    }

    @PostMapping()
    public void add(@Valid @RequestBody ProductDTO productDTO) {
        productService.add(productDTO);
    }

    @PutMapping("/{id}")
    public void update(@Valid @RequestBody ProductDTO productDTO, @PathVariable Long id) {
        productService.updateProduct(productDTO, id);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);

    }
}
