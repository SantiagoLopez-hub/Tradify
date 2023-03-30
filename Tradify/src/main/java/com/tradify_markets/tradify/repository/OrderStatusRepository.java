package com.tradify_markets.tradify.repository;

import com.tradify_markets.tradify.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus, Integer> {
    OrderStatus findByName(String name);
}
