package com.example.analyticsservice.model;

import lombok.*;

@Getter
@RequiredArgsConstructor
public enum CurrencyType {
    RUB("RUB"),
    CNY("CNY");

    private final String currency;
}
