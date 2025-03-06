package com.example.analyticsservice.model;

import java.math.*;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinancialProductModel {

    private Long id;

    private String name;

    private Boolean canDeposit;

    private Boolean canWithdrawal;

    private Boolean capitalizationToSameAccount;

    private BigDecimal percent;

    private String currency;

    private String category;

    private Integer period;

    private String capitalizationPeriod;

}
