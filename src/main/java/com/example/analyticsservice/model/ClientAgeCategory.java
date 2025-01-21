package com.example.analyticsservice.model;

import java.util.*;

import lombok.*;

@Getter
@RequiredArgsConstructor
public enum ClientAgeCategory {

    YOUNG("Молодёжь (14-35)", 14, 35),
    ADULT("Взрослые (35-60)", 35, 60),
    OLD("Пожилые (60+)", 60, Integer.MAX_VALUE);

    private final String category;

    private final int min;

    private final int max;

    public boolean match(int age) {
        return min <= age && age < max;
    }

    public static ClientAgeCategory of(int age) {
        return Arrays.stream(values())
            .filter(value -> value.match(age))
            .findFirst()
            .orElseThrow(() -> new IllegalStateException(String.format(
                "Нет подходящей категории для возраста: %s",
                age
            )));
    }
}
