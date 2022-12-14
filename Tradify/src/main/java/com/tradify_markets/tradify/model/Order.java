package com.tradify_markets.tradify.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Order {
    @Id
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Share share;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @OneToOne(fetch = FetchType.EAGER)
    private OrderType orderType;

    private Integer quantity;
    private Integer price;
    private String date;
    private Boolean isExecuted;
}
