package com.tradify_markets.tradify.service;

import com.tradify_markets.tradify.model.*;
import com.tradify_markets.tradify.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final UserRepository userRepository;
    private final ShareRepository shareRepository;
    private final OrderTypeRepository orderTypeRepository;

    public Order create(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> findByUser(Integer id) {
        return orderRepository.findAllByUser(userRepository.findById(id).orElse(null));
    }

    public List<Order> findExecutedByShare(Integer id) {
        Share share = shareRepository.findById(id).orElse(null);
        OrderStatus status = orderStatusRepository.findByName("Executed");
        OrderType buyOrderType = orderTypeRepository.findByName("Buy");
        return orderRepository.findByShareAndStatusAndOrderTypeOrderByUpdatedAtAsc(share, status, buyOrderType);
    }

    public List<Order> findByUserAndShare(String username, Integer shareId) {
        User user = userRepository.findByUsername(username);
        Share share = shareRepository.findById(shareId).orElse(null);

        return orderRepository.findByUserAndShareOrderByIdDesc(user, share);
    }

    public List<Order> findByShare(Integer shareId) {
        Share share = shareRepository.findById(shareId).orElse(null);

        return orderRepository.findByShareOrderByIdDesc(share);
    }

    public List<Order> findSellOrders(Double price) {
        return orderRepository.findSellOrders(price);
    }

    public void save(Order order) {
        orderRepository.save(order);
    }

    public List<Order> findOpenOrders(User user) {
        return orderRepository.findOpenOrdersByUser(user);
    }
}
