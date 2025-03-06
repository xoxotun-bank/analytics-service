package com.example.analyticsservice.service;

import java.math.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import org.springframework.stereotype.*;

import com.example.analyticsservice.entity.*;
import com.example.analyticsservice.swagger.server.dto.*;

@Service
public class ProductsService {

    public List<ProductDto> getTopOfProducts(
        List<SelectedProductEntity> selectedProducts
    ) {
        var products = new TreeSet<>(Comparator.comparing(ProductDto::getAvgAmount).reversed()
            .thenComparing(ProductDto::getName));

        var avgAmounts = getAverageVal(selectedProducts, SelectedProductEntity::getAmount);
        var avgPeriods = getAverageVal(selectedProducts, this::getProductPeriod);

        for (var avgAmountEntry : avgAmounts.entrySet()) {
            var productName = avgAmountEntry.getKey();

            var avgPeriod = round(avgPeriods.get(productName).getAverage());
            var avgAmount = round(avgAmountEntry.getValue().getAverage());

            var productDto = new ProductDto();
            productDto.setName(productName);
            productDto.setAvgPeriod(avgPeriod);
            productDto.setAvgAmount(avgAmount);

            products.add(productDto);
        }
        return new ArrayList<>(products);
    }

    public BigDecimal getProductPeriod(SelectedProductEntity product) {
        return Optional.of(product)
            .map(SelectedProductEntity::getFinancialProduct)
            .map(FinancialProductEntity::getPeriod)
            .map(BigDecimal::valueOf)
            .orElseThrow();
    }

    private BigDecimal round(double value) {
        return BigDecimal.valueOf(value)
            .setScale(2, RoundingMode.HALF_UP);
    }

    private Map<String, DoubleSummaryStatistics> getAverageVal(
        List<SelectedProductEntity> selectedProducts,
        Function<SelectedProductEntity, BigDecimal> extractField
    ) {

        return selectedProducts.stream()
            .filter(SelectedProductEntity::getIsSuccessfullySelected)
            .collect(Collectors.groupingBy(
                product -> product.getFinancialProduct().getName(),
                Collectors.summarizingDouble(product -> extractField.apply(product).doubleValue()))
            );
    }

}
