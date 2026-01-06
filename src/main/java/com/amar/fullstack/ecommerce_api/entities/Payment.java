package com.amar.fullstack.ecommerce_api.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="order_id",nullable = false)
    private Order order;

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    private Double totalAmount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    private String paymentGateway;
    private String transactionId;

    private LocalDateTime createdAt;

    @PrePersist
    void init(){
        this.createdAt=LocalDateTime.now();
        this.status=PaymentStatus.CREATED;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public String getPaymentGateway() {
        return paymentGateway;
    }

    public void setPaymentGateway(String paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transaqtionId) {
        this.transactionId = transactionId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }


}
