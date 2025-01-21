package com.example.analyticsservice.model;

import java.math.*;
import java.time.*;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelectedProductModel {

    private Long id;

    private LocalDate createdAt;

    private BigDecimal amount;

    private FinancialProductModel financialProduct;

    private Integer clientAge;

    private Boolean isNewClient;

    private Integer userId;

    private String city;

    private String region;

    private Boolean isSuccessfullySelected;

}
