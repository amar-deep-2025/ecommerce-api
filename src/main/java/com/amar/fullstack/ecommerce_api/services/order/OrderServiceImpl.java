package com.amar.fullstack.ecommerce_api.services.order;
import com.amar.fullstack.ecommerce_api.entities.*;
import com.amar.fullstack.ecommerce_api.repository.CartRepository;
import com.amar.fullstack.ecommerce_api.repository.OrderRepository;
import com.amar.fullstack.ecommerce_api.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private CartRepository cartRepo;

    @Override
    @Transactional
    public Order placeOrder() {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Cart cart = cartRepo.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found for user"));

        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }
        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.PAYMENT_PENDING);

        List<OrderItem> items = cart.getItems().stream().map(ci -> {
            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(ci.getProduct());
            item.setQuantity(ci.getQuantity());
            item.setPrice(ci.getProduct().getPrice());
            return item;
        }).toList();

        double totalAmount = items.stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum();

        order.setItems(items);
        order.setTotalAmount(totalAmount);


        Order savedOrder = orderRepository.save(order);


        //cart.getItems().clear();
        cartRepo.save(cart);

        return savedOrder;
    }
}
