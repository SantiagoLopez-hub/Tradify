package com.tradify_markets.tradify.repository;

import com.tradify_markets.tradify.model.Order;
import com.tradify_markets.tradify.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByUser(User user);
}
