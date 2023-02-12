package com.tradify_markets.tradify.route;

import com.tradify_markets.tradify.model.Order;
import com.tradify_markets.tradify.model.User;
import com.tradify_markets.tradify.model.UserShare;
import com.tradify_markets.tradify.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/users")
@RequiredArgsConstructor
public class Users {
    private final UserService userService;

    @GetMapping
    public List<User> users() {
        return userService.findAll();
    }

    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public User user(@PathVariable Integer id) {
        return userService.findById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Integer id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

    @GetMapping("/{id}/shares")
    public List<UserShare> userShares(@PathVariable Integer id) {
        return userService.userShares(id);
    }

    @GetMapping("/{id}/orders")
    public List<Order> userOrders(@PathVariable Integer id) {
        return userService.userOrders(id);
    }
}
