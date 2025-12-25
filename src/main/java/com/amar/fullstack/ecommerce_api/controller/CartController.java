package com.amar.fullstack.ecommerce_api.controller;


import com.amar.fullstack.ecommerce_api.dto.cart.AddToCartRequest;
import com.amar.fullstack.ecommerce_api.dto.cart.CartResponse;
import com.amar.fullstack.ecommerce_api.services.cart.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    //Add to Cart
    @PostMapping("/add")
    public CartResponse addToCart(@Valid @RequestBody AddToCartRequest request){
        System.out.println("Adding to cart Product ID: " + request.getProductId() + ", Quantity: " + request.getQuantity());
        return cartService.addToCart(request);
    }

    //view cart
    @GetMapping
    public CartResponse viewCart(){
        return cartService.viewCart();
    }
}
