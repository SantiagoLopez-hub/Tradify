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
public class UserShare {
    @Id
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Share share;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    private Integer quantity;
}
