package com.example.analyticsservice.service;

import java.math.*;
import java.util.*;

import org.springframework.stereotype.*;

import com.example.analyticsservice.entity.*;
import com.example.analyticsservice.model.*;
import com.example.analyticsservice.swagger.server.dto.*;

@Service
public class PieChartService {

    private final List<ClientAgeCategory> ageCategories = Arrays.stream(ClientAgeCategory.values())
        .toList();

    public List<ChartDto> getPieChart(
        List<SelectedProductEntity> selectedProducts
    ) {
        List<ChartDto> charts = new ArrayList<>();
        var ageCategoryInvestedMoney = getChart(selectedProducts);
        for (var entry : ageCategoryInvestedMoney.entrySet()) {
            var chart = new ChartDto();
            var amount = entry.getValue().setScale(2, RoundingMode.HALF_UP);
            chart.setLabel(entry.getKey());
            chart.setValues(List.of(amount));
            charts.add(chart);
        }
        return charts;
    }

    private Map<String, BigDecimal> getChart(
        List<SelectedProductEntity> selectedProducts
    ) {
        Map<String, BigDecimal> ageCategoryInvestedMoney = new HashMap<>();

        ageCategories.forEach(ageCategory -> {
            ageCategoryInvestedMoney.put(ageCategory.getCategory(), BigDecimal.ZERO);
        });

        selectedProducts.stream()
            .filter(SelectedProductEntity::getIsSuccessfullySelected)
            .forEach(selectedProduct -> {
                var clientCategory = ClientAgeCategory.of(selectedProduct.getClientAge())
                    .getCategory();
                ageCategoryInvestedMoney.compute(
                    clientCategory, (financialProductName, value) ->
                        value.add(selectedProduct.getAmount())
                );
            });
        return ageCategoryInvestedMoney;
    }

}
