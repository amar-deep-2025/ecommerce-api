package com.amar.fullstack.ecommerce_api.repository;

import com.amar.fullstack.ecommerce_api.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
