package com.amar.fullstack.ecommerce_api.repository;

import com.amar.fullstack.ecommerce_api.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

}
