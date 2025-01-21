package com.example.analyticsservice.model;

import lombok.*;

@Getter
@RequiredArgsConstructor
public enum InvestedMoneyUnit {

    RUB_UNIT(100000),
    CNY_UNIT(10000);

    private final int amount;

    public static InvestedMoneyUnit of(CurrencyType currency) {
        switch (currency) {
            case RUB -> {
                return RUB_UNIT;
            }
            case CNY -> {
                return CNY_UNIT;
            }
            default -> throw new BadRequestConfigurationException("currency - " + currency);
        }
    }

}
