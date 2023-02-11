package com.tradify_markets.tradify.service;

import com.tradify_markets.tradify.model.Role;
import com.tradify_markets.tradify.model.User;
import com.tradify_markets.tradify.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
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

    private final String GREEN_LETTERS = "\u001B[32m";
    private final String RESET_LETTERS = "\u001B[0m";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository, userShareService, orderService, passwordEncoder);
    }

    @Test
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

        System.out.println(GREEN_LETTERS + "Expected Value: " + 3 + RESET_LETTERS);
        System.out.println(GREEN_LETTERS + "Actual Value: " + users.size() + RESET_LETTERS);
    }

    @Test
    void saveUser() {
        // Given
        User user = createUser(4);

        // When
        when(userService.saveUser(user)).thenReturn(user);

        // Then
        assertEquals(user, userService.saveUser(user));

        System.out.println(GREEN_LETTERS + "Expected Value: " + user.getUsername() + RESET_LETTERS);
        System.out.println(GREEN_LETTERS + "Actual Value: " + userService.saveUser(user).getUsername() + RESET_LETTERS);
    }

    @Test
    void findByUsername() {
        // Given
        User user = createUser(5);

        // When
        when(userService.findByUsername(user.getUsername())).thenReturn(user);

        // Then
        assertEquals(user, userService.findByUsername(user.getUsername()));

        System.out.println(GREEN_LETTERS + "Expected Value: " + user.getUsername() + RESET_LETTERS);
        System.out.println(GREEN_LETTERS + "Actual Value: " +
                userService.findByUsername(user.getUsername()).getUsername() + RESET_LETTERS);
    }

    @Test
    void findById() {
        // Given
        User user = createUser(6);

        // When
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        // Then
        assertEquals(user, userService.findById(user.getId()));

        System.out.println(GREEN_LETTERS + "Expected Value: " + user.getId() + RESET_LETTERS);
        System.out.println(GREEN_LETTERS + "Actual Value: " +
                userService.findById(user.getId()).getId() + RESET_LETTERS);
    }

    @Test
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

        System.out.println(GREEN_LETTERS + "Expected Value: " + user.getEmail() + RESET_LETTERS);
        System.out.println(GREEN_LETTERS + "Actual Value: " +
                userService.updateUser(user.getId(), user2).getEmail() + RESET_LETTERS);
    }

    @Test
    void deleteUser() {
        // Given
        User user = createUser(9);
        when(userService.saveUser(user)).thenReturn(user);
        userService.saveUser(user);

        // When
        doNothing().when(userRepository).deleteById(9);

        // Then
        userService.deleteUser(user.getId());
        verify(userRepository, times(1)).deleteById(user.getId());
    }

    @Test
    void userShares() {
    }

    @Test
    void userOrders() {
    }

    @Test
    void loadUserByUsername() {
        // Given
        User user = createUser(10);

        // When
        when(userRepository.findByUsername(user.getUsername())).thenReturn(user);

        // Then
        Assertions.assertEquals(user.getUsername(), userRepository.findByUsername(user.getUsername()).getUsername());

        System.out.println(GREEN_LETTERS + "Expected Value: " + user.getUsername() + RESET_LETTERS);
        System.out.println(GREEN_LETTERS + "Actual Value: " +
                userService.loadUserByUsername(user.getUsername()).getUsername() + RESET_LETTERS);
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
}