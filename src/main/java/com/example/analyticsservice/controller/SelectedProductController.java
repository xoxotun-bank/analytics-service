package com.example.analyticsservice.controller;

import java.time.*;
import java.util.*;

import com.fasterxml.jackson.databind.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.*;
import org.modelmapper.*;
import org.springframework.format.annotation.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.transaction.annotation.*;
import org.springframework.web.bind.annotation.*;

import com.example.analyticsservice.exception.*;
import com.example.analyticsservice.model.*;
import com.example.analyticsservice.provider.*;
import com.example.analyticsservice.service.*;
import com.example.analyticsservice.swagger.server.api.*;
import com.example.analyticsservice.swagger.server.dto.*;

@RestController
@RequestMapping("api/v1/analytics")
@RequiredArgsConstructor
public class SelectedProductController implements SelectedProductsApi {

    private final SelectedProductService selectedProductService;

    private final FinancialProductService financialProductService;

    private final ScopeAuthProvider provider;

    private final ModelMapper modelMapper;

    private final GetAnalyticsService getAnalyticsService;

    @Override
    @PreAuthorize("hasRole('operator')")
    @Transactional
    public ResponseEntity<Void> selectedProducts(SelectedProductRequestDto body) {
        validateBody(body);

        var productModel = financialProductService.addFinancialProduct(body.getFinancialProduct());

        var selectedModel = modelMapper.map(body, SelectedProductModel.class);
        selectedModel.setFinancialProduct(productModel);

        UserModel user = provider.getUserInfo();

        selectedModel.setUserId(user.getUserId());
        selectedModel.setCity(user.getCity());
        selectedModel.setRegion(user.getRegion());

        selectedProductService.addSelectedProduct(selectedModel);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public Optional<ObjectMapper> getObjectMapper() {
        return Optional.empty();
    }

    @Override
    public Optional<HttpServletRequest> getRequest() {
        return Optional.empty();
    }

    @Override
    public ResponseEntity<AnalyticsResponseDto> getAnalytics(
        String currency,
        String period,
        @DateTimeFormat(pattern = "dd.MM.yy") LocalDate startDate,
        @DateTimeFormat(pattern = "dd.MM.yy") LocalDate endDate,
        String city
    ) {

        if (period == null) {
            if (startDate == null || endDate == null) {
                throw new BadRequestConfigurationException(
                    "period - any, startDate - null или endDate - null");
            }
        }

        var analyticsResponse = getAnalyticsService.getAnalytics(
            currency,
            period,
            city,
            startDate,
            endDate);
        if (analyticsResponse == null) {
            throw new SelectedProductsNotFoundException();
        }
        return ResponseEntity.ok(analyticsResponse);
    }

    private void validateBody(SelectedProductRequestDto body) {
        nullCheckObject(body.isIsSuccessfullySelected(), body.isIsNewClient(), "isNewClient");
        nullCheckObject(
            body.isIsSuccessfullySelected(),
            body.getClientBirthDate(),
            "clientBirthDate");

        validateClient(
            body.isIsSuccessfullySelected(),
            body.isIsNewClient(),
            body.getClientBirthDate());
    }

    private void validateClient(
        Boolean isSuccessfullySelected,
        Boolean isNewClient,
        LocalDate clientBirthDate
    ) {

        if (isSuccessfullySelected) {
            return;
        }

        if (isNewClient != null) {

            if (isNewClient && clientBirthDate == null) {
                throw new BadRequestConfigurationException(
                    "isSuccessfullySelected - false, isNewClient - true, clientBirthDate - null"
                );
            }

            if (clientBirthDate == null) {
                throw new BadRequestConfigurationException(
                    "isSuccessfullySelected - false, isNewClient - false, clientBirthDate - null"
                );
            }
        }

        if (isNewClient == null && clientBirthDate != null) {
            throw new BadRequestConfigurationException(
                "isSuccessfullySelected - false, isNewClient - null, clientBirthDate - not null"
            );
        }

    }

    private void nullCheckObject(Boolean isSuccessfullySelected, Object object, String fieldName) {
        if (!isSuccessfullySelected) {
            return;
        }
        if (object == null) {
            throw new BadRequestConfigurationException(String.format(
                "isSuccessfullySelected - true, %s - null",
                fieldName));
        }
    }

}
