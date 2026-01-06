package com.amar.fullstack.ecommerce_api.dto.Payment;

public class VerifyPaymentRequestDto {

    private Long paymantId;
    private String razorpayOrderId;
    private String getRazorpayPaymentId;
    private String razorpaySignature;

    public Long getPaymantId() {
        return paymantId;
    }

    public void setPaymantId(Long paymantId) {
        this.paymantId = paymantId;
    }

    public String getRazorpayOrderId() {
        return razorpayOrderId;
    }

    public void setRazorpayOrderId(String razorpayOrderId) {
        this.razorpayOrderId = razorpayOrderId;
    }

    public String getGetRazorpayPaymentId() {
        return getRazorpayPaymentId;
    }

    public void setGetRazorpayPaymentId(String getRazorpayPaymentId) {
        this.getRazorpayPaymentId = getRazorpayPaymentId;
    }

    public String getRazorpaySignature() {
        return razorpaySignature;
    }

    public void setRazorpaySignature(String razorpaySignature) {
        this.razorpaySignature = razorpaySignature;
    }
}
