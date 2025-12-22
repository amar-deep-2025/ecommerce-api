package com.amar.fullstack.ecommerce_api.services;

import com.amar.fullstack.ecommerce_api.dto.ProductRequestDto;
import com.amar.fullstack.ecommerce_api.dto.ProductResponseDto;
import com.amar.fullstack.ecommerce_api.entities.Product;
import com.amar.fullstack.ecommerce_api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository repo;

    @Override
    public ProductResponseDto create(ProductRequestDto dto){
        Product product=new Product();

        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());

        product.setRating(dto.getRating());

        Product saved=repo.save(product);

        return mapToDto(saved);
    }

    @Override
    public List<ProductResponseDto> getAll() {

        return repo.findAll().stream()
                .map(this::mapToDto).toList();
    }

    @Override
    public ProductResponseDto update(Long id, ProductRequestDto dto){

        Product product=repo.findById(id).orElseThrow(()->new RuntimeException("Product not found"));
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());
        product.setRating(dto.getRating());
        Product updated=repo.save(product);
        return mapToDto(updated);


    }

    @Override
    public void delete(Long id){
        repo.deleteById(id);
    }

    //convert entity to dto
    @Override
    public ProductResponseDto mapToDto(Product product){
        ProductResponseDto res=new ProductResponseDto();

        res.setId(product.getId());
        res.setName(product.getName());
        res.setPrice(product.getPrice());
        res.setDescription(product.getDescription());
        res.setRating(product.getRating());

        return res;
    }
}
