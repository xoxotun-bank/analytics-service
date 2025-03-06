package com.example.analyticsservice.service;

import java.math.*;
import java.time.*;
import java.util.*;

import org.springframework.stereotype.*;

import com.example.analyticsservice.entity.*;
import com.example.analyticsservice.model.*;
import com.example.analyticsservice.swagger.server.dto.*;

import static com.example.analyticsservice.model.LineChartType.*;

@Service
public class LineChartService {

    public List<LocalDate> getXAxis(
        LocalDate startDate,
        LocalDate endDate
    ) {
        var step = Period.ofDays(1);
        return startDate.datesUntil(endDate.plusDays(1), step).toList();
    }

    public ChartDto getInvestedMoneyChart(
        List<LocalDate> xAxis,
        String currency,
        List<SelectedProductEntity> products
    ) {

        var investedMoneyChart = getDaysMap(xAxis);

        products.stream()
            .filter(SelectedProductEntity::getIsSuccessfullySelected)
            .forEach(product -> {
                var selectedProductAmount = product.getAmount();
                var creationDate = product.getCreatedAt();
                investedMoneyChart.computeIfAbsent(creationDate, k -> BigDecimal.ZERO);
                investedMoneyChart.computeIfPresent(
                    creationDate, (k, v) -> v.add(selectedProductAmount));
            });

        for (var entry : investedMoneyChart.entrySet()) {
            var moneyUnits = getInvestedMoneyUnits(entry.getValue(), currency);
            investedMoneyChart.put(entry.getKey(), moneyUnits);
        }
        var currencyType = CurrencyType.valueOf(currency);
        var chartType = LineChartType.getInvestedMoneyType(currencyType);
        return getChart(chartType.getType(), investedMoneyChart);
    }

    public ChartDto getNumOfClientsChart(
        List<LocalDate> xAxis,
        List<SelectedProductEntity> products
    ) {

        var newClientsChart = getDaysMap(xAxis);

        products.stream()
            .filter(product -> product.getIsNewClient()!=null)
            .filter(SelectedProductEntity::getIsNewClient)
            .filter(SelectedProductEntity::getIsSuccessfullySelected)
            .forEach(product -> {
                var creationDate = product.getCreatedAt();
                newClientsChart.computeIfAbsent(creationDate, k -> BigDecimal.ZERO);
                newClientsChart.computeIfPresent(creationDate, (k, v) -> v.add(BigDecimal.ONE));
            });

        var chartDto = getChart(NUM_OF_CLIENTS.getType(), newClientsChart);

        return chartDto;
    }

    public ChartDto getSelectedProductsChart(
        List<LocalDate> xAxis,
        List<SelectedProductEntity> products
    ) {

        var selectedProductsChart = getDaysMap(xAxis);

        products.stream()
            .filter(SelectedProductEntity::getIsSuccessfullySelected)
            .forEach(product -> {
                    var creationDate = product.getCreatedAt();
                    selectedProductsChart.computeIfAbsent(creationDate, k -> BigDecimal.ZERO);
                    selectedProductsChart.computeIfPresent(
                        creationDate, (k, v) -> v.add(BigDecimal.ONE));
                }
            );

        var chartDto = getChart(SELECTED_PRODUCTS.getType(), selectedProductsChart);

        return chartDto;
    }

    private BigDecimal getInvestedMoneyUnits(BigDecimal investedMoney, String currency) {
        var currencyType = CurrencyType.valueOf(currency);
        var unit = InvestedMoneyUnit.of(currencyType);
        return investedMoney.divide(
            BigDecimal.valueOf(unit.getAmount()),
            2,
            RoundingMode.HALF_UP
        );
    }

    private ChartDto getChart(
        String label,
        Map<LocalDate, BigDecimal> chart
    ) {

        var chartDto = new ChartDto();

        var yAxis = chart.values().stream()
            .map(value -> value.setScale(2, RoundingMode.HALF_UP))
            .toList();

        chartDto.setLabel(label);
        chartDto.setValues(yAxis);

        return chartDto;
    }

    private Map<LocalDate, BigDecimal> getDaysMap(
        List<LocalDate> xAxis
    ) {
        Map<LocalDate, BigDecimal> daysMap =
            new TreeMap<>(Comparator.comparing(LocalDate::toEpochDay));

        for (var point : xAxis) {
            daysMap.put(point, BigDecimal.valueOf(0));
        }

        return daysMap;
    }

}
