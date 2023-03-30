package com.tradify_markets.tradify.route;

import com.tradify_markets.tradify.model.*;
import com.tradify_markets.tradify.repository.OrderStatusRepository;
import com.tradify_markets.tradify.repository.OrderTypeRepository;
import com.tradify_markets.tradify.service.OrderService;
import com.tradify_markets.tradify.service.UserService;
import com.tradify_markets.tradify.service.UserShareService;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.List;

@Controller
@AllArgsConstructor
public class OrderBook {
    private OrderService orderService;
    private UserService userService;
    private OrderTypeRepository orderTypeRepository;
    private OrderStatusRepository orderStatusRepository;
    private UserShareService userShareService;

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
        Integer status = order.getStatus().getId();
        OrderStatus orderStatus = orderStatusRepository.findById(status).orElse(null);
        assert orderStatus != null;

        if (order.getQuantity() <= 0 || order.getPrice() <= 0) {
            throw new IllegalArgumentException("Quantity and Price must be more than 0");
        }

        // Set all values
        order.setUser(user);
        order.setOrderType(orderType);
        order.setStatus(orderStatus);
        order.setCreatedAt(new Date());
        verifyBalances(order, user);

        if (order.getOrderType().getId() == 1) {
            executeBuyTransaction(order);
        } else if (order.getOrderType().getId() == 2) {
            executeSellTransaction(order);
        }

        return orderService.create(order);
    }

    private void executeSellTransaction(Order seller) {
        List<Order> orders = orderService.findBuyOrders(seller.getPrice());

        if (orders.size() > 0) {
            for (Order order : orders) {
                if (order.getQuantity() > seller.getQuantity()) {
                    buyTransaction(order, seller);
                } else if (order.getQuantity() < seller.getQuantity()) {
                    buyTransaction(seller, order);
                } else {
                    equalTransaction(order, seller);
                }
            }
        }
    }

    public void executeBuyTransaction(Order buyer) {
        List<Order> orders = orderService.findSellOrders(buyer.getPrice());

        if (orders.size() > 0) {
            for (Order order : orders) {
                if (order.getQuantity() > buyer.getQuantity()) {
                    sellTransaction(buyer, order);
                } else if (order.getQuantity() < buyer.getQuantity()) {
                    sellTransaction(order, buyer);
                } else {
                    equalTransaction(buyer, order);
                }
            }
        }
    }

    private void equalTransaction(Order from, Order to) {
        updateBalances(from, to);

        from.setQuantity(0);
        to.setQuantity(0);
        from.setStatus(orderStatusRepository.findByName("Executed"));
        to.setStatus(orderStatusRepository.findByName("Executed"));
        from.setUpdatedAt(new Date());
        to.setUpdatedAt(new Date());
        orderService.save(from);
        orderService.save(to);
    }

    private void buyTransaction(Order order, Order seller) {
        updateBalances(seller, order);

        // Update buyer order
        order.setQuantity(order.getQuantity() - seller.getQuantity());
        order.setUpdatedAt(new Date());
        orderService.save(order);

        // Update seller order
        seller.setQuantity(0);
        seller.setStatus(orderStatusRepository.findByName("Executed"));
        seller.setUpdatedAt(new Date());
        orderService.save(seller);
    }

    public void sellTransaction(Order from, Order to) {
        updateBalances(from, to);

        to.setQuantity(to.getQuantity() - from.getQuantity());
        from.setQuantity(0);
        from.setStatus(orderStatusRepository.findByName("Executed"));
        from.setUpdatedAt(new Date());
        orderService.save(from);
        orderService.save(to);
    }

    private void updateBalances(Order from, Order to) {
        // Update buyer balance
        from.getUser().setBalance(from.getUser().getBalance() - from.getPrice() * from.getQuantity());
        userService.save(from.getUser());

        // Update seller balance
        to.getUser().setBalance(to.getUser().getBalance() + from.getPrice() * from.getQuantity());
        userService.save(to.getUser());

        // Update buyer shares
        UserShare userShare = userShareService.findByUser(from.getUser().getId()).get(0);
        userShare.setQuantity(userShare.getQuantity() + from.getQuantity());
        userShareService.save(userShare);

        // Update seller shares
        UserShare sellerShare = userShareService.findByUser(to.getUser().getId()).get(0);
        sellerShare.setQuantity(sellerShare.getQuantity() - from.getQuantity());
        userShareService.save(sellerShare);
    }

    public void verifyBalances(Order order, User user) {
        List<Order> openOrders = orderService.findOpenOrders(user);

        if (order.getOrderType().getId() == 1) {
            if (user.getBalance() < order.getPrice() * order.getQuantity() +
                    openOrders.stream().mapToDouble(o -> o.getPrice() * o.getQuantity()).sum()) {
                throw new IllegalArgumentException("Insufficient balance");
            }
        }

        if (order.getOrderType().getId() == 2) {
            List<UserShare> userShares = userShareService.findByUser(user.getId());

            if (userShares.size() == 0) {
                throw new IllegalArgumentException("You don't have any shares");
            }
            if (userShares.get(0).getQuantity() < order.getQuantity() +
                    openOrders.stream().mapToInt(Order::getQuantity).sum()) {
                throw new IllegalArgumentException("You don't have enough shares");
            }
        }
    }
}
