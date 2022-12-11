package com.tradify_markets.tradify.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    private Integer id;

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String city;
    private String postCode;
    private String country;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> role = new ArrayList<>();

    public User(Integer id, String email, String password, String firstName, String lastName, String phoneNumber, String address, String city, String postCode, String country) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.postCode = postCode;
        this.country = country;
    }
}
