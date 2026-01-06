package com.amar.fullstack.ecommerce_api.services.cart;

import com.amar.fullstack.ecommerce_api.dto.cart.AddToCartRequest;
import com.amar.fullstack.ecommerce_api.dto.cart.CartResponse;
import com.amar.fullstack.ecommerce_api.dto.cart.UpdateCartRequest;
import com.amar.fullstack.ecommerce_api.entities.Cart;
import com.amar.fullstack.ecommerce_api.entities.User;

public interface CartService {

    CartResponse addToCart(AddToCartRequest request);

    CartResponse viewCart();

    CartResponse updateQuantity(UpdateCartRequest request);

    CartResponse removeItemFromCart(Long productId);

    Cart getCartForLoggedUser();

    void clearCart(User user);
}
