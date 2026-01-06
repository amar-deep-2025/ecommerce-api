package com.amar.fullstack.ecommerce_api.services.Payment;

import com.amar.fullstack.ecommerce_api.dto.Payment.CreatePaymentRequestDto;
import com.amar.fullstack.ecommerce_api.dto.Payment.CreatePaymentResponseDto;
import com.amar.fullstack.ecommerce_api.dto.Payment.VerifyPaymentRequestDto;
import com.amar.fullstack.ecommerce_api.dto.Payment.VerifyPaymentResponseDto;

public interface PaymentService {

    CreatePaymentResponseDto createPayment(CreatePaymentRequestDto createPaymentRequestDto);

    VerifyPaymentResponseDto verifyPayment(VerifyPaymentRequestDto verifyPaymentRequestDto);
}
