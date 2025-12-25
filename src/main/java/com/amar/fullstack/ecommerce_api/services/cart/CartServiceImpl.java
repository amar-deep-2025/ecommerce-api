package com.amar.fullstack.ecommerce_api.services.cart;

import com.amar.fullstack.ecommerce_api.dto.cart.AddToCartRequest;
import com.amar.fullstack.ecommerce_api.dto.cart.CartItemResponse;
import com.amar.fullstack.ecommerce_api.dto.cart.CartResponse;
import com.amar.fullstack.ecommerce_api.entities.Cart;
import com.amar.fullstack.ecommerce_api.entities.CartItem;
import com.amar.fullstack.ecommerce_api.entities.Product;
import com.amar.fullstack.ecommerce_api.entities.User;
import com.amar.fullstack.ecommerce_api.repository.CartItemRepository;
import com.amar.fullstack.ecommerce_api.repository.CartRepository;
import com.amar.fullstack.ecommerce_api.repository.ProductRepository;
import com.amar.fullstack.ecommerce_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.LineNumberInputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private CartItemRepository cartItemRepo;

    @Autowired
    private ProductRepository prodRepo;

    @Autowired
    private UserRepository userRepo;


    @Override
    public CartResponse addToCart(AddToCartRequest request) {
        String email= SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userRepo.findByEmail(email).orElseThrow(()->new RuntimeException("User not found"));

        Cart cart=cartRepo.findByUser(user).orElseGet(()->cartRepo.save(new Cart(user)));

        Product product=prodRepo.findById(request.getProductId()).orElseThrow(()->new RuntimeException("Product not found"));

        CartItem existingItem=cart.getItems().stream()
                .filter(item->item.getProduct().getId().equals(product.getId()))
                .findFirst()
                .orElse(null);

        if (existingItem!=null){
            existingItem.setQuantity(existingItem.getQuantity()+ request.getQuantity());
        }else{
            CartItem item=new CartItem(cart,product, request.getQuantity());
            cart.getItems().add(item);
        }
        cartRepo.save(cart);
        return buildCartResponse(cart);
    }

    private CartResponse buildCartResponse(Cart cart){
        List<CartItemResponse> items=new ArrayList<>();

        double grandTotal=0.0;

        for (CartItem item:cart.getItems()){
            CartItemResponse res=new CartItemResponse();
            res.setProductId(item.getProduct().getId());
            res.setProductName(item.getProduct().getName());
            res.setPrice(item.getProduct().getPrice());
            res.setQuantity(item.getQuantity());

            double totalPrice=item.getProduct().getPrice()*item.getQuantity();
            res.setTotalPrice(totalPrice);
            grandTotal+=totalPrice;
            items.add(res);

        }

        CartResponse response=new CartResponse();
        response.setItems(items);
        response.setGrandTotal(grandTotal);
        return  response;
    }

    @Override
    public CartResponse viewCart() {
        String email=SecurityContextHolder.getContext().getAuthentication().getName();

        User user=userRepo.findByEmail(email)
                .orElseThrow(()->new RuntimeException());

        Cart cart=cartRepo.findByUser(user)
                .orElseThrow(()->new RuntimeException("Cart not found or empty"));

        return buildCartResponse(cart);
    }
}
