package com.tradify_markets.tradify.route;

import com.tradify_markets.tradify.model.*;
import com.tradify_markets.tradify.repository.OrderStatusRepository;
import com.tradify_markets.tradify.repository.OrderTypeRepository;
import com.tradify_markets.tradify.service.OrderService;
import com.tradify_markets.tradify.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class OrderBook {
    private OrderService orderService;
    private UserService userService;
    private OrderTypeRepository orderTypeRepository;
    private OrderStatusRepository orderStatusRepository;

    @MessageMapping("/create")
    @SendTo("/subscribe-orders/order-book")
    public Order create(@Payload Order order) {
        // Get User
        String username = order.getUser().getUsername();
        User user = userService.findByUsername(username);
        assert user != null;

        // Get Order Type
        Integer type = order.getOrderType().getId();
        OrderType orderType = orderTypeRepository.findById(type).orElse(null);
        assert orderType != null;

        // Get Status
        Integer status = order.getOrderType().getId();
        OrderStatus orderStatus = orderStatusRepository.findById(status).orElse(null);
        assert orderStatus != null;

        // Set all values
        order.setUser(user);
        order.setOrderType(orderType);
        order.setStatus(orderStatus);

        return orderService.create(order);
    }
}
