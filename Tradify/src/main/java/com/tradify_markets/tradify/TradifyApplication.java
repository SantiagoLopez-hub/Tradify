package com.tradify_markets.tradify;

import com.tradify_markets.tradify.repository.*;
import com.tradify_markets.tradify.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@AllArgsConstructor
public class TradifyApplication {
    private final RoleRepository roleRepository;
    private final ShareRepository shareRepository;
    private final OrderTypeRepository orderTypeRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final OrderRepository orderRepository;
    private final UserShareRepository userShareRepository;
    private final NewsRepository newsRepository;

    public static void main(String[] args) {
        SpringApplication.run(TradifyApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
            TestData testData = new TestData(userService, roleRepository, shareRepository,
                    orderTypeRepository, orderStatusRepository, orderRepository,
                    userShareRepository, newsRepository);

            testData.createTestData();
        };
    }
}
