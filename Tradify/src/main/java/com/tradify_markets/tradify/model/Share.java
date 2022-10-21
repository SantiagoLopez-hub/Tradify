package com.tradify_markets.tradify.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Share {
    @Id
    private String symbol;
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
