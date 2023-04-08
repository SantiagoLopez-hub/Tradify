package com.tradify_markets.tradify.service;

import com.tradify_markets.tradify.model.*;
import com.tradify_markets.tradify.repository.UserRepository;
import com.tradify_markets.tradify.utility.LetterColour;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceTest {
    private UserService userService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserShareService userShareService;
    @Mock
    private OrderService orderService;
    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository, userShareService, orderService, passwordEncoder);
    }

    @Test
    @org.junit.jupiter.api.Order(1)
    void findAll() {
        // Given
        List<User> users = spy(List.class);

        User user1 = createUser(1);
        User user2 = createUser(2);
        User user3 = createUser(3);
        users.add(user1);
        users.add(user2);
        users.add(user3);

        verify(users).add(user1);
        verify(users).add(user2);
        verify(users).add(user3);

        // When
        doReturn(3).when(users).size();
        assertEquals(3, users.size());

        // Then
        doReturn(3).when(users).size();
        verify(users).size();
        assertEquals(3, users.size());

        System.out.println(LetterColour.GREEN.getColour() +
                "Expected Value: " + 3 +
                LetterColour.RESET.getColour());
        System.out.println(LetterColour.GREEN.getColour() +
                "Actual Value: " + users.size() +
                LetterColour.RESET.getColour());
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    void saveUser() {
        // Given
        User user = createUser(4);

        // When
        when(userService.encodeAndSave(user)).thenReturn(user);

        // Then
        assertEquals(user, userService.encodeAndSave(user));

        System.out.println(LetterColour.GREEN.getColour() +
                "Expected Value: " + user.getUsername() +
                LetterColour.RESET.getColour());
        System.out.println(LetterColour.GREEN.getColour() +
                "Actual Value: " + userService.encodeAndSave(user).getUsername() +
                LetterColour.RESET.getColour());
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    void findByUsername() {
        // Given
        User user = createUser(5);

        // When
        when(userService.findByUsername(user.getUsername())).thenReturn(user);

        // Then
        assertEquals(user, userService.findByUsername(user.getUsername()));

        System.out.println(LetterColour.GREEN.getColour() +
                "Expected Value: " + user.getUsername() +
                LetterColour.RESET.getColour());
        System.out.println(LetterColour.GREEN.getColour() +
                "Actual Value: " + userService.findByUsername(user.getUsername()).getUsername() +
                LetterColour.RESET.getColour());
    }

    @Test
    @org.junit.jupiter.api.Order(4)
    void findById() {
        // Given
        User user = createUser(6);

        // When
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        // Then
        assertEquals(user, userService.findById(user.getId()));

        System.out.println(LetterColour.GREEN.getColour() +
                "Expected Value: " + user.getId() + LetterColour.RESET.getColour());
        System.out.println(LetterColour.GREEN.getColour() + "Actual Value: " +
                userService.findById(user.getId()).getId() + LetterColour.RESET.getColour());
    }

    @Test
    @org.junit.jupiter.api.Order(5)
    void updateUser() {
        // Given
        User user = createUser(7);
        User user2 = createUser(8);
        when(user2.getEmail()).thenReturn("new_email@gold.ac.uk");
        doAnswer(invocation -> "new_email@gold.ac.uk").when(user).getEmail();

        // When
        when(userService.updateUser(user.getId(), user2)).thenReturn(user);

        // Then
        assertEquals(user.getEmail(), userService.updateUser(user.getId(), user2).getEmail());

        System.out.println(LetterColour.GREEN.getColour() + "Expected Value: " +
                user.getEmail() + LetterColour.RESET.getColour());
        System.out.println(LetterColour.GREEN.getColour() + "Actual Value: " +
                userService.updateUser(user.getId(), user2).getEmail() + LetterColour.RESET.getColour());
    }

    @Test
    @org.junit.jupiter.api.Order(6)
    void deleteUser() {
        // Given
        User user = createUser(9);
        when(userService.encodeAndSave(user)).thenReturn(user);
        userService.encodeAndSave(user);

        // When
        doNothing().when(userRepository).deleteById(9);

        // Then
        userService.deleteUser(user.getId());
        verify(userRepository, times(1)).deleteById(user.getId());
    }

    @Test
    @org.junit.jupiter.api.Order(7)
    void userShares() {
        // Given
        User user = createUser(10);
        Share share1 = createShare(1);
        Share share2 = createShare(2);
        UserShare userShare1 = createUserShare(1, share1, user);
        UserShare userShare2 = createUserShare(2, share2, user);

        List<UserShare> userShares = new ArrayList<>();
        userShares.add(userShare1);
        userShares.add(userShare2);

        // When
        when(userService.userShares(user.getId())).thenReturn(userShares);

        // Then
        assertEquals(userShares, userService.userShares(user.getId()));

        System.out.println(LetterColour.GREEN.getColour() + "Expected Value: "
                + userShares + LetterColour.RESET.getColour());
        System.out.println(LetterColour.GREEN.getColour() + "Actual Value: " +
                userService.userShares(user.getId()) + LetterColour.RESET.getColour());
    }

    @Test
    @org.junit.jupiter.api.Order(8)
    void userOrders() {
        // Given
        User user = createUser(11);
        Share share1 = createShare(3);
        Share share2 = createShare(4);
        Share share3 = createShare(5);

        Order order1 = createOrder(1, share1, user);
        Order order2 = createOrder(2, share2, user);
        Order order3 = createOrder(3, share3, user);

        List<Order> orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);
        orders.add(order3);

        // When
        when(userService.userOrders(user.getId())).thenReturn(orders);

        // Then
        assertEquals(orders, userService.userOrders(user.getId()));

        System.out.println(LetterColour.GREEN.getColour() + "Expected Value: "
                + orders + LetterColour.RESET.getColour());
        System.out.println(LetterColour.GREEN.getColour() + "Actual Value: " +
                userService.userOrders(user.getId()) + LetterColour.RESET.getColour());
    }

    @Test
    @org.junit.jupiter.api.Order(9)
    void loadUserByUsername() {
        // Given
        User user = createUser(12);

        // When
        when(userRepository.findByUsername(user.getUsername())).thenReturn(user);

        // Then
        assertEquals(user.getUsername(), userRepository.findByUsername(user.getUsername()).getUsername());

        System.out.println(LetterColour.GREEN.getColour() + "Expected Value: " +
                user.getUsername() + LetterColour.RESET.getColour());
        System.out.println(LetterColour.GREEN.getColour() + "Actual Value: " +
                userService.loadUserByUsername(user.getUsername()).getUsername() + LetterColour.RESET.getColour());
    }

    private User createUser(Integer id) {
        Role role = Role.builder()
                .id(1)
                .name("User")
                .build();

        User user = mock(User.class);
        when(user.getId()).thenReturn(id);
        when(user.getRole()).thenReturn(role);
        when(user.getFirstName()).thenReturn("Adrian");
        when(user.getLastName()).thenReturn("Resin");
        when(user.getAddress()).thenReturn("1st Street");
        when(user.getCity()).thenReturn("London");
        when(user.getCountry()).thenReturn("United Kingdom");
        when(user.getPostCode()).thenReturn("SE1 6D");
        when(user.getPhoneNumber()).thenReturn("07323456789");
        when(user.getUsername()).thenReturn("MyUsername");
        when(user.getPassword()).thenReturn("paSSword1!");
        when(user.getEmail()).thenReturn("email@test.com");

        return user;
    }

    private Share createShare(Integer id) {
        Share share = mock(Share.class);
        when(share.getId()).thenReturn(id);
        when(share.getSymbol()).thenReturn("AAPL");
        when(share.getName()).thenReturn("Apple Inc.");
        when(share.getPrice()).thenReturn(100);

        return share;
    }

    private UserShare createUserShare(Integer id, Share share, User user) {
        UserShare userShare = mock(UserShare.class);
        when(userShare.getId()).thenReturn(id);
        when(userShare.getShare()).thenReturn(share);
        when(userShare.getUser()).thenReturn(user);

        return userShare;
    }

    private Order createOrder(Integer id, Share share, User user) {
        Order order = mock(Order.class);
        when(order.getId()).thenReturn(id);
        when(order.getShare()).thenReturn(share);
        when(order.getUser()).thenReturn(user);

        return order;
    }
}
