package com.tradify_markets.tradify;

import com.tradify_markets.tradify.model.*;
import com.tradify_markets.tradify.repository.*;
import com.tradify_markets.tradify.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

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

            shareRepository.save(
                    Share.builder()
                            .id(1)
                            .name("Discover Financial Services")
                            .price(100)
                            .build()
            );
            shareRepository.save(
                    Share.builder()
                            .id(2)
                            .name("Apple")
                            .price(100)
                            .build()
            );
            shareRepository.save(
                    Share.builder()
                            .id(3)
                            .name("American Express")
                            .price(100)
                            .build()
            );
            shareRepository.save(
                    Share.builder()
                            .id(4)
                            .name("HSBC")
                            .price(100)
                            .build()
            );
            shareRepository.save(
                    Share.builder()
                            .id(5)
                            .name("JP Morgan Chase")
                            .price(100)
                            .build()
            );

            orderStatusRepository.save(
                    OrderStatus.builder()
                            .id(1)
                            .name("Open")
                            .build()
            );
            orderStatusRepository.save(
                    OrderStatus.builder()
                            .id(2)
                            .name("Executed")
                            .build()
            );
            orderStatusRepository.save(
                    OrderStatus.builder()
                            .id(3)
                            .name("Cancelled")
                            .build()
            );

            orderTypeRepository.save(
                    OrderType.builder()
                            .id(1)
                            .name("Buy")
                            .build()
            );
            orderTypeRepository.save(
                    OrderType.builder()
                            .id(2)
                            .name("Sell")
                            .build()
            );

            orderRepository.save(
                    Order.builder()
                            .id(1)
                            .user(userService.findById(1))
                            .share(shareRepository.findById(1).get())
                            .orderType(orderTypeRepository.findById(1).get())
                            .quantity(4)
                            .price(200)
                            .status(orderStatusRepository.findById(1).get())
                            .date(String.valueOf(LocalDate.now().minusDays(1)))
                            .build()
            );
            orderRepository.save(
                    Order.builder()
                            .id(2)
                            .user(userService.findById(1))
                            .share(shareRepository.findById(1).get())
                            .orderType(orderTypeRepository.findById(1).get())
                            .quantity(2)
                            .price(100)
                            .status(orderStatusRepository.findById(2).get())
                            .date(String.valueOf(LocalDate.now().minusDays(4)))
                            .build()
            );
            orderRepository.save(
                    Order.builder()
                            .id(3)
                            .user(userService.findById(1))
                            .share(shareRepository.findById(1).get())
                            .orderType(orderTypeRepository.findById(2).get())
                            .quantity(9)
                            .price(1000)
                            .status(orderStatusRepository.findById(3).get())
                            .date(String.valueOf(LocalDate.now().minusDays(5)))
                            .build()
            );

            userShareRepository.save(
                    UserShare.builder()
                            .id(1)
                            .share(shareRepository.findById(1).get())
                            .user(userService.findById(1))
                            .quantity(10)
                            .build()
            );
            userShareRepository.save(
                    UserShare.builder()
                            .id(2)
                            .share(shareRepository.findById(1).get())
                            .user(userService.findById(1))
                            .quantity(1)
                            .build()
            );

            newsRepository.save(
                    NewsModel.builder()
                            .id(1)
                            .title("Discover Financial Services - Quarterly Earnings Report")
                            .content("Discover Financial Services is an American financial services " +
                                    "company headquartered in Riverwoods, Illinois, a suburb of Chicago. " +
                                    "It was founded in 1986 by Sears, Roebuck and Company, and is now a direct " +
                                    "banking and payment services company. Discover Financial Services is a " +
                                    "member of the S&P 500 index.")
                            .share(shareRepository.findById(1).get())
                            .date(String.valueOf(LocalDate.now().minusDays(1)))
                            .build()
            );
            newsRepository.save(
                    NewsModel.builder()
                            .id(2)
                            .title("Dividend Yield for Discover Financial Services")
                            .content("Discover Financial Services is an American financial services " +
                                    "company headquartered in Riverwoods, Illinois, a suburb of " +
                                    "Chicago. It was founded in 1986 by Sears, Roebuck and Company, " +
                                    "and is now a direct banking and payment services company. Discover " +
                                    "Financial Services is a member of the S&P 500 index.")
                            .share(shareRepository.findById(1).get())
                            .date(String.valueOf(LocalDate.now().minusDays(30)))
                            .build()
            );
            newsRepository.save(
                    NewsModel.builder()
                            .id(3)
                            .title("Breaking: Discover Financial Services to buy back $1.5 billion of shares")
                            .content("Discover Financial Services is an American financial services " +
                                    "company headquartered in Riverwoods, Illinois, a suburb of Chicago. " +
                                    "It was founded in 1986 by Sears, Roebuck and Company, and is now " +
                                    "a direct banking and payment services company. Discover Financial " +
                                    "Services is a member of the S&P 500 index.")
                            .share(shareRepository.findById(1).get())
                            .date(String.valueOf(LocalDate.now().minusDays(60)))
                            .build()
            );
        };
    }
}
