package com.amar.fullstack.ecommerce_api.controller;


import com.amar.fullstack.ecommerce_api.dto.Payment.CreatePaymentRequestDto;
import com.amar.fullstack.ecommerce_api.dto.Payment.CreatePaymentResponseDto;
import com.amar.fullstack.ecommerce_api.dto.Payment.VerifyPaymentRequestDto;
import com.amar.fullstack.ecommerce_api.dto.Payment.VerifyPaymentResponseDto;
import com.amar.fullstack.ecommerce_api.services.Payment.PaymentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService){
        this.paymentService=paymentService;
    }

    @PostMapping("/create")
    public CreatePaymentResponseDto createPayment(@Valid  @RequestBody CreatePaymentRequestDto dto){
        return paymentService.createPayment(dto);
    }

    @PostMapping("/verify")
    public VerifyPaymentResponseDto verifyPayment(@Valid @RequestBody VerifyPaymentRequestDto dto){
        return paymentService.verifyPayment(dto);
    }
}
