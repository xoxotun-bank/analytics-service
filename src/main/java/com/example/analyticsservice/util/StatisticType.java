package com.example.analyticsservice.util;

import lombok.*;

import com.example.analyticsservice.model.*;

@Getter
@RequiredArgsConstructor
public enum StatisticType {
    SUM_RUB("Объем привлеченных средств", "₽"),
    SUM_CNY("Объем привлеченных средств", "¥"),
    CONVERSION_OF_SUCCESSFULLY_SELECTED_PRODUCTS("Конверсия подборов", "%"),
    NUMBER_OF_SELECTED_PRODUCTS("Кол-во подобранных вкладов", ""),
    NUMBER_OF_NEW_CLIENTS("Кол-во новых клиентов", "");

    private final String name;

    private final String mark;

    public static StatisticType getSumStatisticType(CurrencyType currency) {
        switch (currency) {
            case CNY -> {
                return SUM_CNY;
            }
            case RUB -> {
                return SUM_RUB;
            }
            default -> {
                return null;
            }
        }
    }

}
