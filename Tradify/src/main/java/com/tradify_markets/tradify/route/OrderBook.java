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

        if (order.getOrderType().getId() == 1) {
            executeTransaction(order, user);
        }

        return orderService.create(order);
    }

    public boolean executeTransaction(Order order, User user) {
        verifyBalances(order, user);
        List<Order> sellOrders = orderService.findSellOrders(order.getPrice());

//        order.getUser().setBalance(order.getUser().getBalance() - order.getPrice() * order.getQuantity());
        System.out.println("Shares begin: " + userShareService.findByUser(order.getUser().getId()).get(0).getQuantity());

        System.out.println(sellOrders);
        if (sellOrders.size() > 0) {
            System.out.println("sellOrders: " + sellOrders.size() + " " + sellOrders);
            for (Order sellOrder : sellOrders) {
                System.out.println(sellOrder.getPrice() + " " + order.getPrice() + " " + sellOrder.getQuantity());
                if (sellOrder.getQuantity() > order.getQuantity()) {
                    // Update user balance
                    order.getUser().setBalance(order.getUser().getBalance() - order.getPrice() * order.getQuantity());
                    userService.save(order.getUser());

                    // Update seller balance
                    sellOrder.getUser().setBalance(sellOrder.getUser().getBalance() + order.getPrice() * order.getQuantity());
                    userService.save(sellOrder.getUser());

                    System.out.println("executed");
                    // Update user shares
                    UserShare userShare = userShareService.findByUser(order.getUser().getId()).get(0);
                    userShare.setQuantity(userShare.getQuantity() + order.getQuantity());
                    userShareService.save(userShare);

                    // Update seller shares
                    UserShare sellerShare = userShareService.findByUser(sellOrder.getUser().getId()).get(0);
                    sellerShare.setQuantity(sellerShare.getQuantity() - order.getQuantity());
                    userShareService.save(sellerShare);

                    sellOrder.setQuantity(sellOrder.getQuantity() - order.getQuantity());
                    order.setQuantity(0);
                    order.setStatus(orderStatusRepository.findByName("Executed"));
                    order.setUpdatedAt(new Date());
                    orderService.save(order);
                    orderService.save(sellOrder);
                    return true;
                } else if (sellOrder.getQuantity() < order.getQuantity()) {
                    System.out.println("second");
                    order.setQuantity(order.getQuantity() - sellOrder.getQuantity());
                    sellOrder.setQuantity(0);
                    sellOrder.setStatus(orderStatusRepository.findByName("Executed"));
                    sellOrder.setUpdatedAt(new Date());
                    orderService.save(order);
                    orderService.save(sellOrder);
                } else {
                    System.out.println("third");
                    order.setQuantity(0);
                    sellOrder.setQuantity(0);
                    order.setStatus(orderStatusRepository.findByName("Executed"));
                    sellOrder.setStatus(orderStatusRepository.findByName("Executed"));
                    orderService.save(order);
                    orderService.save(sellOrder);
                    return true;
                }
            }
        }

        System.out.println("Shares end: " + userShareService.findByUser(order.getUser().getId()).get(0).getQuantity());

        return true;
    }

    public void verifyBalances(Order order, User user) {
        List<Order> openOrders = orderService.findOpenOrders(user);

        if (order.getOrderType().getId() == 1) {
            if (user.getBalance() < order.getPrice() * order.getQuantity() + openOrders.stream().mapToDouble(o -> o.getPrice() * o.getQuantity()).sum()) {
                throw new IllegalArgumentException("Insufficient balance");
            }
        }

        if (order.getOrderType().getId() == 2) {
            List<UserShare> userShares = userShareService.findByUser(user.getId());

            if (userShares.size() == 0) {
                throw new IllegalArgumentException("You don't have any shares");
            }
            if (userShares.get(0).getQuantity() < order.getQuantity() + openOrders.stream().mapToInt(Order::getQuantity).sum()) {
                throw new IllegalArgumentException("You don't have enough shares");
            }
        }
    }
}
