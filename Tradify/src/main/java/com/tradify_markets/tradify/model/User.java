package com.tradify_markets.tradify.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {
    @Id
    private Integer id;

    @OneToOne(fetch = FetchType.EAGER)
    private Role role;

//    @OneToMany(fetch = FetchType.LAZY)
//    private List<Order> orders;
//
//    @OneToMany(fetch = FetchType.LAZY)
//    private List<UserShare> userShares;

    @Column(unique = true)
    private String email;

    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String city;
    private String postCode;
    private String country;
}
