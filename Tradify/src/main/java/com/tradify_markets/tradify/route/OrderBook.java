package com.tradify_markets.tradify.route;

import com.tradify_markets.tradify.model.Order;
import com.tradify_markets.tradify.model.User;
import com.tradify_markets.tradify.service.OrderService;
import com.tradify_markets.tradify.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Base64;

@Controller
@AllArgsConstructor
public class OrderBook {
    private OrderService orderService;
    private UserService userService;

    @MessageMapping("/create")
    @SendTo("/subscribe-orders/order-book")
    public Order create(@Payload Order order) {
        String username = order.getUser().getUsername();
        User user = userService.findByUsername(username);
        assert user != null;
        order.setUser(user);

        return orderService.create(order);
    }
}
