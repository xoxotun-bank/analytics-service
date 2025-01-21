package com.example.analyticsservice.model;

import java.math.*;
import java.time.*;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CsvSelectedProductModel {

    private Long id;

    private LocalDate createdAt;

    private BigDecimal amount;

    private Integer clientAge;

    private Boolean isNewClient;

    private Integer userId;

    private String city;

    private String region;

    private Boolean isSuccessfullySelected;

    private Long financialProductId;

    private String name;

    private String category;

    private Boolean canDeposit;

    private Boolean canWithdrawal;

    private Boolean capitalizationToSameAccount;

    private BigDecimal percent;

    private String capitalizationPeriod;

    private String currency;

    private Integer period;

}
