package com.tradify_markets.tradify.route;

import com.tradify_markets.tradify.model.Share;
import com.tradify_markets.tradify.model.User;
import com.tradify_markets.tradify.repository.ShareRepository;
import com.tradify_markets.tradify.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class Users {
    private final UserRepository userRepository;
    private final ShareRepository shareRepository;

    public Users(UserRepository userRepository,
                 ShareRepository shareRepository) {
        this.userRepository = userRepository;
        this.shareRepository = shareRepository;
    }

    @GetMapping("")
    public List<User> users() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> user(@PathVariable Integer id) {
        return userRepository.findById(id);
    }

    @GetMapping("/share")
    public List<Share> share() {
        return shareRepository.findAll();
    }
}
