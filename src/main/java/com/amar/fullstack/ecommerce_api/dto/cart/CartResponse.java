package com.amar.fullstack.ecommerce_api.dto.cart;

import java.util.List;

public class CartResponse {

    private List<CartItemResponse> items;
    private Double grandTotal;

    public List<CartItemResponse> getItems() {
        return items;
    }

    public void setItems(List<CartItemResponse> items) {
        this.items = items;
    }

    public Double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(Double grandTotal) {
        this.grandTotal = grandTotal;
    }
}
