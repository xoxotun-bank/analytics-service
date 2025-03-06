package com.example.analyticsservice.service;

import java.time.*;
import java.time.format.*;
import java.util.*;

import lombok.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;

import com.example.analyticsservice.exception.*;
import com.example.analyticsservice.repository.*;
import com.example.analyticsservice.swagger.server.dto.*;
import com.example.analyticsservice.util.*;

@Service
@RequiredArgsConstructor
public class DictionariesService {

    public static final String ERROR_MESSAGE = "Данные о справочниках не найдены";

    public static final int ERROR_CODE = 4007;

    private final FinancialProductRepository financialProductRepository;

    private final SelectedProductRepository selectedProductRepository;

    private final List<String> period = Arrays.stream(PeriodEnum.values())
        .map(PeriodEnum::getName)
        .toList();

    public AnalyticsParametersDto analyticsDict() {

        var result = new AnalyticsParametersDto();
        var currencies = financialProductRepository.findAllCurrencies();
        var cities = selectedProductRepository.findAllCities();

        var startDate = selectedProductRepository.findMinCreatedAt();

        validateResponse(cities, currencies, startDate);

        var formatter = DateTimeFormatter.ofPattern("dd.MM.yy");
        String formattedDate = startDate.format(formatter);

        result.setCities(cities);
        result.setStartDate(formattedDate);
        result.setCurrencies(currencies);
        result.setPeriods(period);

        return result;
    }

    private void validateResponse(
        List<String> cities,
        List<String> currencies,
        LocalDate startDate
    ) {
        if (cities.isEmpty() || currencies.isEmpty() || startDate == null) {
            throw new DefaultException(ERROR_MESSAGE, ERROR_CODE, HttpStatus.NOT_FOUND);
        }
    }

}
