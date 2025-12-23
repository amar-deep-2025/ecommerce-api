package com.amar.fullstack.ecommerce_api.dto.cart;

import java.util.List;

public class CartResponse {

    private List<CartItemResponse> items;
    private Double totalPrice;

    public List<CartItemResponse> getItems() {
        return items;
    }

    public void setItems(List<CartItemResponse> items) {
        this.items = items;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
