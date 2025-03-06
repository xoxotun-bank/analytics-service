package com.example.analyticsservice.controller;

import java.util.*;

import com.fasterxml.jackson.databind.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.web.bind.annotation.*;

import com.example.analyticsservice.service.*;
import com.example.analyticsservice.swagger.server.api.*;
import com.example.analyticsservice.swagger.server.dto.*;

@RestController
@RequestMapping("api/v1/analytics")
@RequiredArgsConstructor
public class DictionariesController implements DictionariesApi {

    private final DictionariesService dictionariesService;

    @Override
    @PreAuthorize("hasRole('administrator')")
    public ResponseEntity<AnalyticsParametersDto> analyticsDict() {
        var dto = dictionariesService.analyticsDict();
        return ResponseEntity.ok(dto);
    }

    @Override
    public Optional<ObjectMapper> getObjectMapper() {
        return Optional.empty();
    }

    @Override
    public Optional<HttpServletRequest> getRequest() {
        return Optional.empty();
    }

}
