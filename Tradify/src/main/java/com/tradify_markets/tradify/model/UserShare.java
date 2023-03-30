package com.tradify_markets.tradify.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class UserShare {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Share share;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @Min(value = 0, message = "Quantity must be greater than 0")
    private Integer quantity;
}
