package com.tradify_markets.tradify.route;

import com.tradify_markets.tradify.model.User;
import com.tradify_markets.tradify.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/users")
public class Users {
    private final UserRepository userRepository;

    public Users(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("")
    public List<User> users() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> user(@PathVariable Integer id) {
        return userRepository.findById(id);
    }
}
