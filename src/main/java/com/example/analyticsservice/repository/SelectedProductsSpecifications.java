package com.example.analyticsservice.repository;

import java.time.*;
import java.util.*;

import jakarta.persistence.criteria.*;

import org.springframework.data.jpa.domain.*;

import com.example.analyticsservice.entity.*;

public class SelectedProductsSpecifications {

    public static Specification<SelectedProductEntity> betweenDateByCurrencyAndCityIfPresent(
        LocalDate fromDate,
        LocalDate toDate,
        String currency,
        String city
    ) {
        return (selectedProduct, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            var greaterThan = builder.greaterThanOrEqualTo(
                selectedProduct.get("createdAt"),
                fromDate);
            var lessThan = builder.lessThanOrEqualTo(selectedProduct.get("createdAt"), toDate);
            predicates.add(greaterThan);
            predicates.add(lessThan);
            if (city != null) {
                predicates.add(builder.equal(selectedProduct.get("city"), city));
            }
            var currencyPredicate = builder.equal(
                selectedProduct.get("financialProduct").get("currency"),
                currency);

            predicates.add(
                builder.and(currencyPredicate)
            );
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

}

