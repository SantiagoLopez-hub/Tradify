package com.tradify_markets.tradify;

import com.tradify_markets.tradify.model.*;
import com.tradify_markets.tradify.repository.*;
import com.tradify_markets.tradify.service.UserService;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class TestData {
    private final UserService userService;
    private final RoleRepository roleRepository;
    private final ShareRepository shareRepository;
    private final OrderTypeRepository orderTypeRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final OrderRepository orderRepository;
    private final UserShareRepository userShareRepository;
    private final NewsRepository newsRepository;

    public void createTestData() {
        createUsers();
        createShares();
        createOrders();
        createUserShares();
        createNews();
    }

    public void createUsers() {
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
                        .role(roleRepository.findById(1).orElse(null))
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
                        .role(roleRepository.findById(2).orElse(null))
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
    }

    public void createShares() {
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
    }

    public void createOrders() {
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


        /*
        *
        * Discover Financial Services
        *
        * */
        orderRepository.save(
                Order.builder()
                        .id(1)
                        .user(userService.findById(1))
                        .share(shareRepository.findById(1).orElse(null))
                        .orderType(orderTypeRepository.findById(1).orElse(null))
                        .quantity(4)
                        .price(200)
                        .status(orderStatusRepository.findById(1).orElse(null))
                        .date(String.valueOf(LocalDate.now().minusDays(1)))
                        .build()
        );
        orderRepository.save(
                Order.builder()
                        .id(2)
                        .user(userService.findById(1))
                        .share(shareRepository.findById(1).orElse(null))
                        .orderType(orderTypeRepository.findById(1).orElse(null))
                        .quantity(2)
                        .price(100)
                        .status(orderStatusRepository.findById(2).orElse(null))
                        .date(String.valueOf(LocalDate.now().minusDays(4)))
                        .build()
        );
        orderRepository.save(
                Order.builder()
                        .id(3)
                        .user(userService.findById(1))
                        .share(shareRepository.findById(1).orElse(null))
                        .orderType(orderTypeRepository.findById(2).orElse(null))
                        .quantity(9)
                        .price(1000)
                        .status(orderStatusRepository.findById(3).orElse(null))
                        .date(String.valueOf(LocalDate.now().minusDays(5)))
                        .build()
        );
        orderRepository.save(
                Order.builder()
                        .id(4)
                        .user(userService.findById(1))
                        .share(shareRepository.findById(1).orElse(null))
                        .orderType(orderTypeRepository.findById(1).orElse(null))
                        .quantity(2)
                        .price(54)
                        .status(orderStatusRepository.findById(2).orElse(null))
                        .date(String.valueOf(LocalDate.now().minusDays(10)))
                        .build()
        );
        orderRepository.save(
                Order.builder()
                        .id(5)
                        .user(userService.findById(1))
                        .share(shareRepository.findById(1).orElse(null))
                        .orderType(orderTypeRepository.findById(1).orElse(null))
                        .quantity(4)
                        .price(76)
                        .status(orderStatusRepository.findById(2).orElse(null))
                        .date(String.valueOf(LocalDate.now().minusDays(30)))
                        .build()
        );
        orderRepository.save(
                Order.builder()
                        .id(6)
                        .user(userService.findById(1))
                        .share(shareRepository.findById(1).orElse(null))
                        .orderType(orderTypeRepository.findById(1).orElse(null))
                        .quantity(34)
                        .price(15)
                        .status(orderStatusRepository.findById(2).orElse(null))
                        .date(String.valueOf(LocalDate.now().minusDays(65)))
                        .build()
        );
        orderRepository.save(
                Order.builder()
                        .id(7)
                        .user(userService.findById(1))
                        .share(shareRepository.findById(1).orElse(null))
                        .orderType(orderTypeRepository.findById(1).orElse(null))
                        .quantity(37)
                        .price(35)
                        .status(orderStatusRepository.findById(2).orElse(null))
                        .date(String.valueOf(LocalDate.now().minusDays(100)))
                        .build()
        );
        orderRepository.save(
                Order.builder()
                        .id(8)
                        .user(userService.findById(1))
                        .share(shareRepository.findById(1).orElse(null))
                        .orderType(orderTypeRepository.findById(1).orElse(null))
                        .quantity(31)
                        .price(20)
                        .status(orderStatusRepository.findById(2).orElse(null))
                        .date(String.valueOf(LocalDate.now().minusDays(145)))
                        .build()
        );


        /*
        *
        * Apple
        *
        * */
        orderRepository.save(
                Order.builder()
                        .id(9)
                        .user(userService.findById(1))
                        .share(shareRepository.findById(2).orElse(null))
                        .orderType(orderTypeRepository.findById(2).orElse(null))
                        .quantity(9)
                        .price(154.73)
                        .status(orderStatusRepository.findById(2).orElse(null))
                        .date(String.valueOf(LocalDate.now().minusDays(300)))
                        .build()
        );
        orderRepository.save(
                Order.builder()
                        .id(10)
                        .user(userService.findById(1))
                        .share(shareRepository.findById(2).orElse(null))
                        .orderType(orderTypeRepository.findById(1).orElse(null))
                        .quantity(2)
                        .price(130)
                        .status(orderStatusRepository.findById(2).orElse(null))
                        .date(String.valueOf(LocalDate.now().minusDays(250)))
                        .build()
        );
        orderRepository.save(
                Order.builder()
                        .id(11)
                        .user(userService.findById(1))
                        .share(shareRepository.findById(2).orElse(null))
                        .orderType(orderTypeRepository.findById(1).orElse(null))
                        .quantity(4)
                        .price(174.55)
                        .status(orderStatusRepository.findById(2).orElse(null))
                        .date(String.valueOf(LocalDate.now().minusDays(150)))
                        .build()
        );
        orderRepository.save(
                Order.builder()
                        .id(12)
                        .user(userService.findById(1))
                        .share(shareRepository.findById(2).orElse(null))
                        .orderType(orderTypeRepository.findById(1).orElse(null))
                        .quantity(34)
                        .price(126.36)
                        .status(orderStatusRepository.findById(2).orElse(null))
                        .date(String.valueOf(LocalDate.now().minusDays(65)))
                        .build()
        );
        orderRepository.save(
                Order.builder()
                        .id(13)
                        .user(userService.findById(1))
                        .share(shareRepository.findById(2).orElse(null))
                        .orderType(orderTypeRepository.findById(1).orElse(null))
                        .quantity(37)
                        .price(148.50)
                        .status(orderStatusRepository.findById(2).orElse(null))
                        .date(String.valueOf(LocalDate.now().minusDays(10)))
                        .build()
        );
        orderRepository.save(
                Order.builder()
                        .id(14)
                        .user(userService.findById(1))
                        .share(shareRepository.findById(2).orElse(null))
                        .orderType(orderTypeRepository.findById(1).orElse(null))
                        .quantity(37)
                        .price(178.96)
                        .status(orderStatusRepository.findById(2).orElse(null))
                        .date(String.valueOf(LocalDate.now().minusDays(270)))
                        .build()
        );
    }

    public void createUserShares() {
        userShareRepository.save(
                UserShare.builder()
                        .id(1)
                        .share(shareRepository.findById(1).orElse(null))
                        .user(userService.findById(1))
                        .quantity(10)
                        .build()
        );
        userShareRepository.save(
                UserShare.builder()
                        .id(2)
                        .share(shareRepository.findById(1).orElse(null))
                        .user(userService.findById(1))
                        .quantity(1)
                        .build()
        );
    }

    public void createNews(){
        newsRepository.save(
                NewsModel.builder()
                        .id(1)
                        .title("Discover Financial Services - Quarterly Earnings Report")
                        .content("Discover Financial Services is an American financial services " +
                                "company headquartered in Riverwoods, Illinois, a suburb of Chicago. " +
                                "It was founded in 1986 by Sears, Roebuck and Company, and is now a direct " +
                                "banking and payment services company. Discover Financial Services is a " +
                                "member of the S&P 500 index.")
                        .share(shareRepository.findById(1).orElse(null))
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
                        .share(shareRepository.findById(1).orElse(null))
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
                        .share(shareRepository.findById(1).orElse(null))
                        .date(String.valueOf(LocalDate.now().minusDays(60)))
                        .build()
        );

        newsRepository.save(
                NewsModel.builder()
                        .id(4)
                        .title("Apple - Quarterly Earnings Report")
                        .content("Apple Inc. is an American multinational technology company " +
                                "headquartered in Cupertino, California, that designs, develops, " +
                                "and sells consumer electronics, computer software, and online " +
                                "services. It is considered one of the Big Four of technology, " +
                                "alongside Amazon, Google, and Facebook.")
                        .share(shareRepository.findById(2).orElse(null))
                        .date(String.valueOf(LocalDate.now().minusDays(60)))
                        .build()
        );
    }
}