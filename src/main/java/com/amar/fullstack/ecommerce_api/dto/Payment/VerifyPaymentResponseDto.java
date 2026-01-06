package com.amar.fullstack.ecommerce_api.dto.Payment;

public class VerifyPaymentResponseDto {

    private String message;

    private String orderStatus;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
