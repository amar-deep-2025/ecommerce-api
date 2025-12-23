package com.amar.fullstack.ecommerce_api.repository;

import com.amar.fullstack.ecommerce_api.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
}
