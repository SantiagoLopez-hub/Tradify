package com.tradify_markets.tradify;

import com.tradify_markets.tradify.model.User;
import com.tradify_markets.tradify.repository.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TradifyApplication {
    @Autowired
    private final UserRepository userRepository;

    public TradifyApplication(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(TradifyApplication.class, args);
    }

    @Bean
    InitializingBean sendDatabase() {
        return () -> {
            userRepository.save(new User(1, "Jhn@gmail.com", "pass3ord", "John",
                    "Doe", "1234456789", "123 Fake", "London", "E1 6D", "UK"));
            userRepository.save(new User(2, "Jgohn@gmail.com", "password", "John",
                    "Doe", "1223456789", "123 Street", "Paris", "E 6BD", "UK"));
            userRepository.save(new User(3, "Jon@gmail.com", "pa8swo1d", "John",
                    "Doe", "1234556789", "12 Fake Street", "Madrid", "1 6BD", "UK"));
            userRepository.save(new User(4, "Jochbn@gmail.com", "as2s1wa33ord", "John",
                    "Doe", "8123456789", "13 Fake Street", "Moscow", "E1 D", "UK"));
            userRepository.save(new User(5, "John@bgmail.com", "p9asw6ord", "John",
                    "Doe", "12384456789", "3 Fake Street", "Milan", "E1 6B", "UK"));
        };
    }
}
