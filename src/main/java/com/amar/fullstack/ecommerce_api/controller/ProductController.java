package com.amar.fullstack.ecommerce_api.controller;

import com.amar.fullstack.ecommerce_api.dto.ProductRequestDto;
import com.amar.fullstack.ecommerce_api.dto.ProductResponseDto;
import com.amar.fullstack.ecommerce_api.services.ProductService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private static final Logger log =
            LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @PostMapping
    public ProductResponseDto create(@Valid @RequestBody ProductRequestDto dto){
        log.info("Create Product API called | name={} | price={}",dto.getName(),dto.getPrice());
        return productService.create(dto);
    }

    @GetMapping
    public List<ProductResponseDto> getAll(){
        log.info("Get All Products API called");
        return productService.getAll();
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @Valid @RequestBody ProductRequestDto dto){
        log.info("Update Product API called | id={} | name={} | price={}",id,dto.getName(),dto.getPrice());
        return productService.update(id,dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        log.info("Delete Product API called | id={}",id);
        System.out.println("Deleted item "+id);
        productService.delete(id);
    }
}

