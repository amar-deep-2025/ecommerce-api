package com.amar.fullstack.ecommerce_api.controller;

import com.amar.fullstack.ecommerce_api.dto.ProductRequestDto;
import com.amar.fullstack.ecommerce_api.dto.ProductResponseDto;
import com.amar.fullstack.ecommerce_api.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ProductResponseDto create(@Valid @RequestBody ProductRequestDto dto){
        return productService.create(dto);
    }

    @GetMapping
    public List<ProductResponseDto> getAll(){
        return productService.getAll();
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @Valid @RequestBody ProductRequestDto dto){
        return productService.update(id,dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        System.out.println("Deleted item "+id);
        productService.delete(id);
    }
}

