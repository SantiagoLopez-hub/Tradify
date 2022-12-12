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
            // Create a new user with builder pattern
            userRepository.save(
                    User.builder()
                            .id(1)
                            .email("Jhn@gmail.com")
                            .password("pass3ord")
                            .firstName("John")
                            .lastName("Doe")
                            .phoneNumber("1234456789")
                            .address("123 Main St")
                            .city("London")
                            .postCode("E1 6D")
                            .country("UK")
                            .build()
            );

            userRepository.save(
                    User.builder()
                            .id(2)
                            .email("Jgohn@gmail.com")
                            .password("password")
                            .firstName("John")
                            .lastName("Doe")
                            .phoneNumber("1223456789")
                            .address("123 Street")
                            .city("Paris")
                            .postCode("E 6BD")
                            .country("UK")
                            .build()
            );
            userRepository.save(
                    User.builder()
                            .id(3)
                            .email("Jon@gmail.com")
                            .password("pa8swo1d")
                            .firstName("John")
                            .lastName("Doe")
                            .phoneNumber("1234556789")
                            .address("12 Fake Street")
                            .city("Madrid")
                            .postCode("1 6BD")
                            .country("UK")
                            .build()
            );
            userRepository.save(
                    User.builder()
                            .id(4)
                            .email("Jochbn@gmail.com")
                            .password("as2s1wa33ord")
                            .firstName("John")
                            .lastName("Doe")
                            .phoneNumber("8123456789")
                            .address("13 Fake Street")
                            .city("Moscow")
                            .postCode("E1 D")
                            .country("UK")
                            .build()
            );
            userRepository.save(
                    User.builder()
                            .id(5)
                            .email("John@bgmail.com")
                            .password("p9asw6ord")
                            .firstName("John")
                            .lastName("Doe")
                            .phoneNumber("12384456789")
                            .address("3 Fake Street")
                            .city("Milan")
                            .postCode("E1 6B")
                            .country("UK")
                            .build()
            );
        };
    }
}
