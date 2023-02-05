package com.tradify_markets.tradify.service;

import com.tradify_markets.tradify.model.Order;
import com.tradify_markets.tradify.repository.OrderRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;

    public OrderService(OrderRepository orderRepository, @Lazy UserService userService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
    }

    public Order findByUser(Integer id) {
        return orderRepository.findByUser(userService.findById(id));
    }
}
