package com.amar.fullstack.ecommerce_api.dto.Payment;

public class CreatePaymentRequestDto {

    private Long ordrerId;

    public Long getOrdrerId() {
        return ordrerId;
    }

    public void setOrdrerId(Long ordrerId) {
        this.ordrerId = ordrerId;
    }
}
