package com.tradify_markets.tradify.route;

import com.tradify_markets.tradify.model.Order;
import com.tradify_markets.tradify.model.User;
import com.tradify_markets.tradify.model.UserShare;
import com.tradify_markets.tradify.repository.RoleRepository;
import com.tradify_markets.tradify.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class Users {
    private final UserService userService;
    private final RoleRepository roleRepository;

    @GetMapping
    public List<User> users() {
        return userService.findAll();
    }

    @PostMapping("/create")
    public User createUser(@RequestParam String firstName,
                           @RequestParam String lastName,
                           @RequestParam String email,
                           @RequestParam String phoneNumber,
                           @RequestParam String address,
                           @RequestParam String city,
                           @RequestParam String postCode,
                           @RequestParam String country,
                           @RequestParam String username,
                           @RequestParam String password) {
        User user = User.builder()
                .role(roleRepository.findByName("User"))
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .phoneNumber(phoneNumber)
                .address(address)
                .city(city)
                .postCode(postCode)
                .country(country)
                .username(username)
                .password(password)
                .build();

        return userService.encodeAndSave(user);
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
    public List<UserShare> getAllUserShares(@PathVariable Integer id) {
        return userService.userShares(id);
    }

    @GetMapping("/{username}/{shareId}")
    public UserShare userShares(@PathVariable String username, @PathVariable Integer shareId) {
        return userService.userShare(username, shareId);
    }

    @GetMapping("/{id}/orders")
    public List<Order> userOrders(@PathVariable Integer id) {
        return userService.userOrders(id);
    }
}
