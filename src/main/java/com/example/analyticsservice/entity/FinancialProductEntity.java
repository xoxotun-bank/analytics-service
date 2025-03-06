package com.example.analyticsservice.entity;

import java.math.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "financial_products")
public class FinancialProductEntity {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "category", nullable = false)
    private String category;

    @NotNull
    @Column(name = "can_deposit", nullable = false)
    private Boolean canDeposit = false;

    @NotNull
    @Column(name = "can_withdrawal", nullable = false)
    private Boolean canWithdrawal = false;

    @NotNull
    @Column(name = "capitalization_to_same_account", nullable = false)
    private Boolean capitalizationToSameAccount = false;

    @NotNull
    @Column(name = "percent", nullable = false)
    private BigDecimal percent;

    @NotNull
    @Column(name = "capitalization_period", nullable = false)
    private String capitalizationPeriod;

    @NotNull
    @Column(name = "currency", nullable = false)
    private String currency;

    @NotNull
    @Column(name = "period", nullable = false)
    private Integer period;

}
