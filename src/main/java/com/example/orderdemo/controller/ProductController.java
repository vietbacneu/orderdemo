package com.example.orderdemo.controller;

import com.example.orderdemo.dto.ProductDTO;
import com.example.orderdemo.service.PageAndSortService;
import com.example.orderdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    PageAndSortService pageAndSortService;

    @GetMapping
    public Page<ProductDTO> getAll(@RequestParam(value = "name", required = false) String name,
                                   @RequestParam(value = "page", required = false) Integer pageable
                                  ,@RequestParam(value = "sort", required = false) String sort,
                                   @RequestParam(value = "min", required = false) Long min,
                                   @RequestParam(value = "max", required = false) Long max
                           ) {
        return pageAndSortService.getProductbyName(pageable,name,sort,min,max);
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
