package com.tradify_markets.tradify;

import com.tradify_markets.tradify.model.*;
import com.tradify_markets.tradify.repository.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class TradifyApplication {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ShareRepository shareRepository;
    private final NewsRepository newsRepository;
    private final OrderRepository orderRepository;
    private final OrderTypeRepository orderTypeRepository;
    private final UserShareRepository userShareRepository;

    public TradifyApplication(UserRepository userRepository, RoleRepository roleRepository, ShareRepository shareRepository, NewsRepository newsRepository, OrderRepository orderRepository, OrderTypeRepository orderTypeRepository, UserShareRepository userShareRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.shareRepository = shareRepository;
        this.newsRepository = newsRepository;
        this.orderRepository = orderRepository;
        this.orderTypeRepository = orderTypeRepository;
        this.userShareRepository = userShareRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(TradifyApplication.class, args);
    }

    @Bean
    InitializingBean sendDatabase() {
        return () -> {
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
                            .role(roleRepository.findById(1).get())
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
                            .role(roleRepository.findById(2).get())
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

            newsRepository.save(
                    News.builder()
                            .id(1)
                            .title("Test")
                            .content("Test")
                            .build()
            );
            shareRepository.save(
                    Share.builder()
                            .id(1)
                            .price(100)
                            .name("Coca Cola")
                            .news(List.of(newsRepository.findById(1).get()))
                            .build()
            );

            orderTypeRepository.save(
                    OrderType.builder()
                            .id(1)
                            .name("Buy")
                            .build()
            );

            orderRepository.save(
                    Order.builder()
                            .id(1)
                            .user(userRepository.findById(1).get())
                            .share(shareRepository.findById(1).get())
                            .orderType(orderTypeRepository.findById(1).get())
                            .price(56.21)
                            .quantity(10)
                            .build()
            );

            userShareRepository.save(
                    UserShare.builder()
                            .id(1)
                            .user(userRepository.findById(1).get())
                            .share(shareRepository.findById(1).get())
                            .quantity(10)
                            .build()
            );
        };
    }
}
