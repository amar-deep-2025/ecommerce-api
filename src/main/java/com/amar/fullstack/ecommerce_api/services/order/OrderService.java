package com.amar.fullstack.ecommerce_api.services.order;

import com.amar.fullstack.ecommerce_api.entities.*;
import com.amar.fullstack.ecommerce_api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


public interface OrderService {

    public Order placeOrder();

}
