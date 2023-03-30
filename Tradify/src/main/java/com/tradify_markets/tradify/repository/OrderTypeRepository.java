package com.tradify_markets.tradify.repository;

import com.tradify_markets.tradify.model.OrderType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderTypeRepository extends JpaRepository<OrderType, Integer> {
    OrderType findByName(String name);
}
