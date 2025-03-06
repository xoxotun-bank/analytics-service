package com.example.analyticsservice.util;

import java.time.*;
import java.time.temporal.*;

import static java.time.temporal.ChronoUnit.*;

import lombok.experimental.*;

@UtilityClass
public class DateUtil {

    public static LocalDate getStartDate(String period, LocalDate endDate) {
        var periodType = PeriodEnum.fromPeriod(period);
        var startDate = switch (periodType) {
            case MONTH -> endDate.withDayOfMonth(1);
            case QUARTER -> endDate.with(IsoFields.DAY_OF_QUARTER, 1);
            case YEAR -> endDate.withDayOfYear(1);
            default -> LocalDate.now();
        };
        return startDate;
    }

    public static LocalDate getStartDateOfPreviousPeriod(
        String period,
        LocalDate currentStartDate,
        LocalDate currentEndDate
    ) {
        if (period == null) {
            var daysBetweenStartAndEndDate = DAYS.between(
                currentStartDate.minusDays(1),
                currentEndDate);
            return currentStartDate.minusDays(daysBetweenStartAndEndDate);
        }

        var periodType = PeriodEnum.fromPeriod(period);
        var startDate = switch (periodType) {
            case MONTH -> currentEndDate.minusMonths(1).withDayOfMonth(1);
            case QUARTER -> currentEndDate
                .with(IsoFields.DAY_OF_QUARTER, 1)
                .minusMonths(1)
                .with(IsoFields.DAY_OF_QUARTER, 1)
                .withDayOfMonth(1);
            case YEAR -> currentEndDate.minusYears(1).withDayOfYear(1);
            default -> LocalDate.now();
        };
        return startDate;
    }

    public static LocalDate getEndDateOfPreviousPeriod(
        String period,
        LocalDate currentStartDate,
        LocalDate startDateOfPreviousPeriod
    ) {

        if (period == null) {
            return currentStartDate.minusDays(1);
        }

        var periodType = PeriodEnum.fromPeriod(period);
        var endDate = switch (periodType) {
            case MONTH -> startDateOfPreviousPeriod.with(TemporalAdjusters.lastDayOfMonth());
            case QUARTER -> startDateOfPreviousPeriod
                .plusMonths(2).with(TemporalAdjusters.lastDayOfMonth());
            case YEAR -> startDateOfPreviousPeriod.with(TemporalAdjusters.lastDayOfYear());
            default -> LocalDate.now();
        };
        return endDate;
    }

}
