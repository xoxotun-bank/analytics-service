package com.example.analyticsservice.service;

import java.math.*;
import java.util.*;

import lombok.*;
import org.springframework.stereotype.*;

import com.example.analyticsservice.entity.*;
import com.example.analyticsservice.model.*;
import com.example.analyticsservice.swagger.server.dto.*;
import com.example.analyticsservice.util.*;

import static com.example.analyticsservice.util.StatisticType.*;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    public StatisticsDto getSum(
        String period,
        String currency,
        List<SelectedProductEntity> currentSelectedProducts,
        List<SelectedProductEntity> previousSelectedProducts
    ) {
        var currentSum = getSum(currentSelectedProducts);
        var previousSum = getSum(previousSelectedProducts);
        var sumGrowth = calculateGrowthValue(
            currentSum,
            previousSum);
        var currencyType = CurrencyType.valueOf(currency);
        var statisticType = StatisticType.getSumStatisticType(currencyType);
        return getStatistic(
            period,
            statisticType,
            currentSum,
            sumGrowth);
    }

    public StatisticsDto getNumOfSelectedProducts(
        String period,
        List<SelectedProductEntity> currentSelectedProducts,
        List<SelectedProductEntity> previousSelectedProducts
    ) {

        var currentCount = countOfSelectedProducts(currentSelectedProducts);
        var previousCount = countOfSelectedProducts(previousSelectedProducts);

        var growth = calculateGrowthValue(
            currentCount,
            previousCount);

        return getStatistic(
            period,
            NUMBER_OF_SELECTED_PRODUCTS,
            currentCount,
            growth);
    }

    public StatisticsDto getConversionOfSuccessfullySelectedProducts(
        String period,
        List<SelectedProductEntity> currentSelectedProducts,
        List<SelectedProductEntity> previousSelectedProducts
    ) {

        var currentSuccess = countOfSuccessfullySelectedProducts(currentSelectedProducts);
        var currentCount = countOfSelectedProducts(currentSelectedProducts);

        var currentPercent = conversionOfSuccessfullySelectedProducts(currentSuccess, currentCount);

        var previousSuccess = countOfSuccessfullySelectedProducts(previousSelectedProducts);
        var previousCount = countOfSelectedProducts(previousSelectedProducts);

        var previousPercent = conversionOfSuccessfullySelectedProducts(previousSuccess, previousCount);

        var growth = calculateGrowthValueForPercent(
            currentPercent,
            previousPercent);

        return getStatistic(
            period,
            CONVERSION_OF_SUCCESSFULLY_SELECTED_PRODUCTS,
            currentPercent,
            growth);
    }

    public StatisticsDto getNumOfNewClients(
        String period,
        List<SelectedProductEntity> currentSelectedProducts,
        List<SelectedProductEntity> previousSelectedProducts
    ) {

        var currentNewClients = countOfNewClients(currentSelectedProducts);
        var previousNewClients = countOfNewClients(previousSelectedProducts);

        var growth = calculateGrowthValue(
            currentNewClients,
            previousNewClients);

        return getStatistic(
            period,
            NUMBER_OF_NEW_CLIENTS,
            currentNewClients,
            growth);
    }

    private BigDecimal getSum(List<SelectedProductEntity> selectedProducts) {
        return selectedProducts.stream()
            .filter(SelectedProductEntity::getIsSuccessfullySelected)
            .map(SelectedProductEntity::getAmount)
            .reduce(
                BigDecimal.ZERO,
                BigDecimal::add
            );
    }

    private BigDecimal countOfSuccessfullySelectedProducts(
        List<SelectedProductEntity> selectedProducts
    ) {
        var count = selectedProducts.stream()
            .filter(SelectedProductEntity::getIsSuccessfullySelected)
            .count();
        return BigDecimal.valueOf(count);
    }

    private BigDecimal countOfSelectedProducts(
        List<SelectedProductEntity> selectedProducts
    ) {
        var count = selectedProducts.size();
        return BigDecimal.valueOf(count);
    }

    private BigDecimal conversionOfSuccessfullySelectedProducts(
        BigDecimal countOfSuccessfullySelected,
        BigDecimal countOfSelectedProducts
    ) {
        if (countOfSelectedProducts.equals(BigDecimal.ZERO)) {
            return null;
        }
        return countOfSuccessfullySelected
            .divide(countOfSelectedProducts, 2, RoundingMode.HALF_UP)
            .multiply(BigDecimal.valueOf(100));
    }

    private BigDecimal countOfNewClients(
        List<SelectedProductEntity> selectedProducts
    ) {
        return BigDecimal.valueOf(selectedProducts.stream()
            .filter(product -> product.getIsNewClient()!=null)
            .filter(SelectedProductEntity::getIsNewClient)
            .filter(SelectedProductEntity::getIsSuccessfullySelected)
            .count());
    }

    private BigDecimal calculateGrowthValue(
        BigDecimal currentValue,
        BigDecimal previousValue
    ) {
        if (previousValue == null || previousValue.equals(BigDecimal.ZERO)) {
            return null;
        }
        var dif = currentValue.subtract(previousValue);
        var growth = dif.divide(
            previousValue,
            2,
            RoundingMode.HALF_UP
        ).multiply(BigDecimal.valueOf(100));
        return growth;
    }

    private BigDecimal calculateGrowthValueForPercent(
        BigDecimal currentPercent,
        BigDecimal previousPercent
    ) {
        if (previousPercent == null) {
            return null;
        }
        return currentPercent.subtract(previousPercent);
    }

    private StatisticsDto getStatistic(
        String period,
        StatisticType statisticType,
        BigDecimal value,
        BigDecimal growth
    ) {
        var name = statisticType.getName();
        var mark = statisticType.getMark();
        var numOfPeriod = StatisticPeriodName.of(period);
        var statistic = new StatisticsDto();
        statistic.setName(name);
        statistic.setValue(value);
        statistic.setGrowthValue(growth);
        statistic.setMark(mark);
        statistic.setNameOfPeriod(numOfPeriod.getName());
        return statistic;
    }

}
