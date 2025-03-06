package com.example.analyticsservice.controller;

import java.time.*;
import java.util.*;

import com.fasterxml.jackson.databind.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.*;
import org.springframework.format.annotation.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.web.bind.annotation.*;

import com.example.analyticsservice.exception.*;
import com.example.analyticsservice.service.*;
import com.example.analyticsservice.swagger.server.api.*;
import com.example.analyticsservice.util.*;

@RestController
@RequestMapping("api/v1/analytics")
@RequiredArgsConstructor
public class ExportController implements ExportStatisticsApi {

    private final AnalyticsCsvService analyticsCsvService;

    @Override
    public Optional<ObjectMapper> getObjectMapper() {
        return Optional.empty();
    }

    @Override
    public Optional<HttpServletRequest> getRequest() {
        return Optional.empty();
    }

    @Override
    @PreAuthorize("hasRole('administrator')")
    public ResponseEntity<String> getCsvReport(
        String currency,
        String period,
        @DateTimeFormat(pattern = "dd.MM.yy") LocalDate startDate,
        @DateTimeFormat(pattern = "dd.MM.yy") LocalDate endDate,
        String city
    ) {
        var startPoint = startDate;
        var endPoint = endDate;

        if (period == null) {
            if (startDate == null) {
                throw new BadRequestConfigurationException("period - any, startDate - null");
            }
            if (endDate == null) {
                throw new BadRequestConfigurationException("period - any, endDate - null");
            }
        } else {
            endPoint = LocalDate.now();
            startPoint = DateUtil.getStartDate(period, endPoint);
        }

        var analyticsResponse = analyticsCsvService.generateCsv(
            currency,
            startPoint,
            endPoint,
            city);

        return new ResponseEntity<>(analyticsResponse, HttpStatus.OK);
    }

}
