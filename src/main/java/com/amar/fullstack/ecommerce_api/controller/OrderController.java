package com.amar.fullstack.ecommerce_api.controller;


import com.amar.fullstack.ecommerce_api.entities.Cart;
import com.amar.fullstack.ecommerce_api.entities.Order;
import com.amar.fullstack.ecommerce_api.entities.User;
import com.amar.fullstack.ecommerce_api.repository.CartRepository;
import com.amar.fullstack.ecommerce_api.services.cart.CartService;
import com.amar.fullstack.ecommerce_api.services.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @PostMapping("/place")
    public Order placeOrder(@AuthenticationPrincipal User user){
        return orderService.placeOrder();
    }

}
