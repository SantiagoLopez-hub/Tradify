package com.tradify_markets.tradify.route;

import com.tradify_markets.tradify.model.Order;
import com.tradify_markets.tradify.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class Orders {
    private final OrderService orderService;

    @GetMapping("/{username}/{shareId}")
    public List<Order> orders(@PathVariable String username, @PathVariable Integer shareId) {
        return orderService.findByUserAndShare(username, shareId);
    }

    @GetMapping("/{shareId}")
    public List<Order> orders(@PathVariable Integer shareId) {
        return orderService.findByShare(shareId);
    }
}
