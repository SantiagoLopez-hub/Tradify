package com.tradify_markets.tradify;

import com.tradify_markets.tradify.model.Role;
import com.tradify_markets.tradify.model.User;
import com.tradify_markets.tradify.repository.RoleRepository;
import com.tradify_markets.tradify.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class TradifyApplication {
    private final RoleRepository roleRepository;

    public TradifyApplication(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

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
            roleRepository.save(
                    Role.builder()
                            .id(1)
                            .name("Admin")
                            .build()
            );
            roleRepository.save(
                    Role.builder()
                            .id(2)
                            .name("User")
                            .build()
            );

            userService.saveUser(
                    User.builder()
                            .id(1)
                            .username("username")
                            .email("Jhn@gmail.com")
                            .password("1234")
                            .firstName("John")
                            .lastName("Doe")
                            .phoneNumber("1234456789")
                            .address("123 Main St")
                            .city("London")
                            .postCode("E1 6D")
                            .country("UK")
                            .role(roleRepository.findById(1).get())
                            .build()
            );
            userService.saveUser(
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
                            .role(roleRepository.findById(2).get())
                            .build()
            );
            userService.saveUser(
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
            userService.saveUser(
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
            userService.saveUser(
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
