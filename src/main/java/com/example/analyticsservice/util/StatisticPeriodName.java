package com.example.analyticsservice.util;

import lombok.*;

@Getter
@RequiredArgsConstructor
public enum StatisticPeriodName {
    MONTH("По сравнению с прошлым месяцем"),
    QUARTER("По сравнению с прошлым кварталом"),
    YEAR("По сравнению с прошлым годом"),
    CUSTOM("По сравнению с предыдущем аналогичным периодом");

    private final String name;

    public static StatisticPeriodName of(String period) {
        if (period == null) {
            return StatisticPeriodName.CUSTOM;
        }
        var periodType = PeriodEnum.fromPeriod(period);
        switch (periodType) {
            case MONTH -> {
                return StatisticPeriodName.MONTH;
            }
            case QUARTER -> {
                return StatisticPeriodName.QUARTER;
            }
            case YEAR -> {
                return StatisticPeriodName.YEAR;
            }
            default -> {
                return null;
            }
        }
    }

}
