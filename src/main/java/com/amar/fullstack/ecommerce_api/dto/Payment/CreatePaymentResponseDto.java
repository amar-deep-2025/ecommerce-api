package com.amar.fullstack.ecommerce_api.dto;

public class CreatePaymentResponseDto {

    private Long paymentId;
    private Double totalAmount;
    private String gatewayUrl;
    private String razorpayOrderId;
}
