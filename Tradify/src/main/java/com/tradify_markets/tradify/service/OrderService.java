package com.tradify_markets.tradify.service;

import com.tradify_markets.tradify.model.Order;
import com.tradify_markets.tradify.model.Share;
import com.tradify_markets.tradify.model.User;
import com.tradify_markets.tradify.repository.OrderRepository;
import com.tradify_markets.tradify.repository.ShareRepository;
import com.tradify_markets.tradify.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ShareRepository shareRepository;

    public List<Order> findByUser(Integer id) {
        return orderRepository.findAllByUser(userRepository.findById(id).orElse(null));
    }

    public List<Order> findByUserAndShare(String username, Integer shareId) {
        User user = userRepository.findByUsername(username);
        Share share = shareRepository.findById(shareId).orElse(null);

        return orderRepository.findByUserAndShare(user, share);
    }
}
