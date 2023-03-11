package com.tradify_markets.tradify.route;

import com.tradify_markets.tradify.model.Order;
import com.tradify_markets.tradify.model.Share;
import com.tradify_markets.tradify.repository.ShareRepository;
import com.tradify_markets.tradify.service.OrderService;
import com.tradify_markets.tradify.service.ShareService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shares")
@RequiredArgsConstructor
public class Shares {
    private final ShareService shareService;
    private final OrderService orderService;

    @GetMapping
    public List<Share> shares() {
        return shareService.findAll();
    }

    @GetMapping("/{id}")
    public Share getShare(@PathVariable Integer id) {
        return shareService.findById(id);
    }

    @GetMapping("/{id}/trading-history")
    public List<Order> orders(@PathVariable Integer id) {
        return orderService.findExecutedByShare(id);
    }

    @PutMapping("/{id}")
    public Share updateShare(@PathVariable Integer id, @RequestBody Share share) {
        return shareService.updateShare(id, share);
    }

    @DeleteMapping("/{id}")
    public void deleteShare(@PathVariable Integer id) {
        shareService.deleteById(id);
    }
}
