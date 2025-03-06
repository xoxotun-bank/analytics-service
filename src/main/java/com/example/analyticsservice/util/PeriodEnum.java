package com.example.analyticsservice.util;

import lombok.*;

@Getter
@RequiredArgsConstructor
public enum PeriodEnum {
    MONTH("Месяц"),
    QUARTER("Квартал"),
    YEAR("Год");

    private final String name;

    public static PeriodEnum fromPeriod(String period) {
        for (PeriodEnum periodEnum : PeriodEnum.values()) {
            if (periodEnum.getName().equals(period)) {
                return periodEnum;
            }
        }
        return null;
    }

}
