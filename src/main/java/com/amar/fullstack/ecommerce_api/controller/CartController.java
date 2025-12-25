package com.amar.fullstack.ecommerce_api.controller;


import com.amar.fullstack.ecommerce_api.dto.cart.AddToCartRequest;
import com.amar.fullstack.ecommerce_api.dto.cart.CartResponse;
import com.amar.fullstack.ecommerce_api.dto.cart.UpdateCartRequest;
import com.amar.fullstack.ecommerce_api.services.cart.CartService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private static final Logger log =
            LoggerFactory.getLogger(CartController.class);
    @Autowired
    private CartService cartService;

    //Add to Cart
    @PostMapping("/add")
    public CartResponse addToCart(@Valid @RequestBody AddToCartRequest request){
        log.info("ADD TO CART API called | productId={} | quantity={}",request.getProductId(),request.getQuantity());
        return cartService.addToCart(request);
    }

    //view cart
    @GetMapping
    public CartResponse viewCart(){
        log.info("VIEW CART API called");
        return cartService.viewCart();
    }

    //update cart item quantity
    @PutMapping("/update")
    public CartResponse updateQuantity(@Valid @RequestBody UpdateCartRequest request){
        log.info("UPDATE CART API called | productId={} | quantity={}",request.getProductId(),request.getQuantity());
        return cartService.updateQuantity(request);
    }

    @DeleteMapping("/remove/{productId}")
    public CartResponse removeItemFromCart(@PathVariable Long productId){
        log.info("REMOVE FROM CART API called | productId={}",productId);
        return cartService.removeItemFromCart(productId);
    }


}
