package com.tradify_markets.tradify.route;

import com.tradify_markets.tradify.model.Order;
import com.tradify_markets.tradify.model.User;
import com.tradify_markets.tradify.model.UserShare;
import com.tradify_markets.tradify.repository.OrderRepository;
import com.tradify_markets.tradify.repository.UserRepository;
import com.tradify_markets.tradify.repository.UserShareRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class Users {
    private final UserRepository userRepository;
    private final UserShareRepository userShareRepository;
    private final OrderRepository orderRepository;

    public Users(UserRepository userRepository,
                 UserShareRepository userShareRepository,
                 OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.userShareRepository = userShareRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public List<User> users() {
        return userRepository.findAll();
    }

    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/{id}")
    public Optional<User> user(@PathVariable Integer id) {
        return userRepository.findById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Integer id, @RequestBody User user) {
        user.setId(id);
        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userRepository.deleteById(id);
    }

    @GetMapping("/{id}/shares")
    public UserShare userShares(@PathVariable Integer id) {
        return userShareRepository.findByUser(userRepository.findById(id).get());
    }

    @GetMapping("/{id}/orders")
    public Order userOrders(@PathVariable Integer id) {
        return orderRepository.findByUser(userRepository.findById(id).get());
    }
}
