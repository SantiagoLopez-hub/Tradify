package com.tradify_markets.tradify.repository;

import com.tradify_markets.tradify.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByUser(User user);

    List<Order> findByUserAndShareOrderByIdDesc(User user, Share share);

    List<Order> findByShareAndStatusOrderByUpdatedAtAsc(Share share, OrderStatus status);

    @Query("SELECT o FROM Order o WHERE o.user = ?1 AND o.status.id = 1")
    List<Order> findOpenOrdersByUser(User user);

    @Query("SELECT o FROM Order o WHERE o.share = ?1 AND o.status.id = 1 ORDER BY o.createdAt DESC")
    List<Order> findByShareOrderByIdDesc(Share share);

    @Query("SELECT o FROM Order o WHERE o.orderType.id=1 AND o.status.id = 1 AND o.price >= ?1 ORDER BY o.createdAt ASC")
    List<Order> findBuyOrders(Double price);

    @Query("SELECT o FROM Order o WHERE o.orderType.id=2 AND o.status.id = 1 AND o.price <= ?1 ORDER BY o.createdAt ASC")
    List<Order> findSellOrders(Double price);
}
