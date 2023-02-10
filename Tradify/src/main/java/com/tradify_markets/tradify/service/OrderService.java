package com.tradify_markets.tradify.service;

import com.tradify_markets.tradify.model.Order;
import com.tradify_markets.tradify.repository.OrderRepository;
import com.tradify_markets.tradify.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public Order findByUser(Integer id) {
        return orderRepository.findByUser(userRepository.findById(id).orElse(null));
    }
}
