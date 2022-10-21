package com.tradify_markets.tradify.route;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Users {
    @GetMapping("/")
    public String home() {
        return "Hello World!";
    }
}
