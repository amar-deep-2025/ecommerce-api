package com.amar.fullstack.ecommerce_api.services;


import com.amar.fullstack.ecommerce_api.dto.ProductRequestDto;
import com.amar.fullstack.ecommerce_api.dto.ProductResponseDto;
import com.amar.fullstack.ecommerce_api.entities.Product;
import com.amar.fullstack.ecommerce_api.repository.ProductRepository;

import java.util.List;

public interface ProductService {

    ProductResponseDto create(ProductRequestDto dto);

    List<ProductResponseDto> getAll();

    ProductResponseDto update(Long id, ProductRequestDto dto);


    void delete(Long id);

    //convert entity to dto
    ProductResponseDto mapToDto(Product product);
}
