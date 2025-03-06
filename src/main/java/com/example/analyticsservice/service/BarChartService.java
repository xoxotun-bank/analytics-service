package com.example.analyticsservice.service;

import java.math.*;
import java.util.*;

import static java.math.BigDecimal.*;

import lombok.*;
import org.springframework.stereotype.*;

import com.example.analyticsservice.entity.*;
import com.example.analyticsservice.model.*;
import com.example.analyticsservice.swagger.server.dto.*;

@Service
@RequiredArgsConstructor
public class BarChartService {

    public AnalyticsResponseDtoBarChart getBarChartDto(
        List<SelectedProductEntity> selectedProducts
    ) {
        var ageCategoryToProductCounts = getChart(selectedProducts);
        var barChartDto = new AnalyticsResponseDtoBarChart();
        List<ChartDto> bars = new ArrayList<>();

        List<String> productNames = ageCategoryToProductCounts.values().stream()
            .map(Map::keySet)
            .flatMap(Set::stream)
            .distinct()
            .toList();

        for (var entry : ageCategoryToProductCounts.entrySet()) {
            var counts = entry.getValue().values().stream()
                .map(count -> count.toCount().setScale(2, RoundingMode.HALF_UP))
                .toList();
            var chartDto = new ChartDto();
            chartDto.setLabel(entry.getKey());
            chartDto.setValues(counts);
            bars.add(chartDto);
        }

        barChartDto.setXAxis(productNames);
        barChartDto.setBars(bars);
        return barChartDto;
    }

    private static class CountAccumulator {

        private BigDecimal count = ZERO;

        private void inc(
            ClientAgeCategory category,
            SelectedProductEntity product
        ) {
            count = getCategory(product).equals(category)
                ? count.add(product.getAmount())
                : count;
        }

        public BigDecimal toCount() {
            return count;
        }

        private ClientAgeCategory getCategory(SelectedProductEntity p) {
            return ClientAgeCategory.of(p.getClientAge());
        }

    }

    private Map<String, Map<String, CountAccumulator>> getChart(
        List<SelectedProductEntity> products
    ) {

        var ageCategoryToProductCounts = new HashMap<String, Map<String, CountAccumulator>>();

        var ageCategories = Arrays.stream(ClientAgeCategory.values())
            .toList();

        for (var ageCategory : ageCategories) {
            products.stream()
                .filter(SelectedProductEntity::getIsSuccessfullySelected)
                .forEach(product -> ageCategoryToProductCounts
                    .computeIfAbsent(ageCategory.getCategory(), k -> new HashMap<>())
                    .computeIfAbsent(
                        product.getFinancialProduct().getName(),
                        k -> new CountAccumulator())
                    .inc(ageCategory, product));
        }
        return ageCategoryToProductCounts;
    }

}
