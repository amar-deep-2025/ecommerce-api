package com.amar.fullstack.ecommerce_api.dto;

import jakarta.validation.constraints.*;

public class ProductRequestDto {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @NotBlank(message = "name is required")
    private String name;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private Double price;

    @Size(max = 1000, message = "Description must be max 1000 characters")
    private String description;

    @Min(value = 0, message = "rating must be at least 0")
    @Max(value = 5, message = "rating must be max 5")
    private Double rating = 0.0;

    
}
