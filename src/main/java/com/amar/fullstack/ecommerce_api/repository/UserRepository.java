package com.amar.fullstack.ecommerce_api.repository;

import com.amar.fullstack.ecommerce_api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
