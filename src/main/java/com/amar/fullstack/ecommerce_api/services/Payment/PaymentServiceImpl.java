package com.amar.fullstack.ecommerce_api.services.Payment;

import com.amar.fullstack.ecommerce_api.dto.Payment.CreatePaymentRequestDto;
import com.amar.fullstack.ecommerce_api.dto.Payment.CreatePaymentResponseDto;
import com.amar.fullstack.ecommerce_api.dto.Payment.VerifyPaymentRequestDto;
import com.amar.fullstack.ecommerce_api.dto.Payment.VerifyPaymentResponseDto;
import com.amar.fullstack.ecommerce_api.entities.Order;
import com.amar.fullstack.ecommerce_api.entities.Payment;
import com.amar.fullstack.ecommerce_api.entities.PaymentStatus;
import com.amar.fullstack.ecommerce_api.repository.PaymentRepository;
import com.amar.fullstack.ecommerce_api.services.cart.CartService;
import com.amar.fullstack.ecommerce_api.services.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @Override
    public CreatePaymentResponseDto createPayment(CreatePaymentRequestDto dto) {

        Order order = orderService.getOrderById(dto.getOrdrerId());

        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setTotalAmount(order.getTotalAmount());
        payment.setPaymentGateway("ROZARPAY");

        // ( razorpay order id will be created)
        CreatePaymentResponseDto responseDto = new CreatePaymentResponseDto();
        responseDto.setPaymentId(payment.getId());
        responseDto.setTotalAmount(payment.getTotalAmount());
        responseDto.setGatewayUrl(payment.getPaymentGateway());
        responseDto.setRazorpayOrderId("generated_by_gateway");

        return responseDto;
    }

    @Override
    public VerifyPaymentResponseDto verifyPayment(VerifyPaymentRequestDto dto) {
        Payment payment = paymentRepository.findById(dto.getPaymantId())
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        // Signature Verification
        payment.setStatus(PaymentStatus.SUCCESS);
        payment.setTransactionId(dto.getGetRazorpayPaymentId());
        paymentRepository.save(payment);

        // update order
        orderService.markOrderPaid(payment.getOrder());

        // Clear cart After
        cartService.clearCart(payment.getOrder().getUser());

        VerifyPaymentResponseDto response = new VerifyPaymentResponseDto();
        response.setMessage("Payment successful");
        response.setOrderStatus("PAID");
        return response;
    }
}
