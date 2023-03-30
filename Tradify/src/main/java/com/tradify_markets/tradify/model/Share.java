package com.tradify_markets.tradify.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Share {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(targetEntity = NewsModel.class, cascade = CascadeType.ALL)
    @Builder.Default
    private List<NewsModel> news = new ArrayList<>();

    private String symbol;
    private Integer price;
    private String name;
    private String sector;
    private String industry;
    private String description;
    private String exchange;
    private String currency;
    private String country;
    private String address;
    private String city;
    private String postCode;
    private String website;
    private String logo;
    private String colour;

    private String ipoDate;
    private String marketCapitalization;
    private String fiftyTwoWeekHigh;
    private String fiftyTwoWeekLow;
    private String fiftyTwoWeekHighDate;
    private String fiftyTwoWeekLowDate;

    private String annualDividendRate;
    private String payoutRatio;
    private String dividendDate;
    private String exDividendDate;
    private String forwardEps;
    private String revenuePerShare;
    private String totalCash;
    private String totalDebt;
}
