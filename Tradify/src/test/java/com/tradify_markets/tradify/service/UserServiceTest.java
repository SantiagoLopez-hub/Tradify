package com.tradify_markets.tradify.service;

import com.tradify_markets.tradify.model.Role;
import com.tradify_markets.tradify.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class UserServiceTest {
    @Mock
    private UserService userService;

    private final String GREEN_LETTERS = "\u001B[32m";
    private final String RESET_LETTERS = "\u001B[0m";

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {
        // Given
        assert true;

        // When
        // Then
    }

    @Test
    void saveUser() {
        // Given
        Role role = Role.builder()
                .id(1)
                .name("User")
                .build();

        User user = mock(User.class);
        when(user.getId()).thenReturn(1);
        when(user.getRole()).thenReturn(role);
        when(user.getFirstName()).thenReturn("Adrian");
        when(user.getLastName()).thenReturn("Resin");
        when(user.getAddress()).thenReturn("1st Street");
        when(user.getCity()).thenReturn("London");
        when(user.getCountry()).thenReturn("United Kingdom");
        when(user.getPostCode()).thenReturn("SE1 6D");
        when(user.getPhoneNumber()).thenReturn("07323456789");
        when(user.getUsername()).thenReturn("username");
        when(user.getPassword()).thenReturn("paSSword1!");
        when(user.getEmail()).thenReturn("email@test.com");

        // When
        Mockito.when(userService.saveUser(user)).thenReturn(user);

        // Then
        assertEquals(user, userService.saveUser(user));

        System.out.println(GREEN_LETTERS + "Expected Value: " + user.getUsername() + RESET_LETTERS);
        System.out.println(GREEN_LETTERS + "Actual Value: " + userService.saveUser(user).getUsername() + RESET_LETTERS);
    }

    @Test
    void findByUsername() {
    }

    @Test
    void findById() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void userShares() {
    }

    @Test
    void userOrders() {
    }

    @Test
    void loadUserByUsername() {
    }
}