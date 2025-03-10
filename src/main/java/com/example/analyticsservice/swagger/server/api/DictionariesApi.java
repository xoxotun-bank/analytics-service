/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.57).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */

package com.example.analyticsservice.swagger.server.api;

import java.util.*;

import jakarta.servlet.http.*;

import org.slf4j.*;

import com.fasterxml.jackson.databind.*;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.security.*;
import org.springframework.http.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;

import com.example.analyticsservice.swagger.server.dto.*;

@Validated
public interface DictionariesApi {

    Logger log = LoggerFactory.getLogger(DictionariesApi.class);

    Optional<ObjectMapper> getObjectMapper();

    Optional<HttpServletRequest> getRequest();

    @Operation(summary = "Получить значения справочные значения для ананлитики", description = "Запрос на получение справочной информации аналитики", security = {
        @SecurityRequirement(name = "BearerAuth")}, tags = {"Analytics"})
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Запись успешно добавлена", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AnalyticsParametersDto.class))),

        @ApiResponse(responseCode = "404", description = "Справочные значения не найдены", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponseDto.class))),

        @ApiResponse(responseCode = "403", description = "У пользователя нет доступа", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponseDto.class))),

        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponseDto.class))),

        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponseDto.class)))})
    @RequestMapping(value = "/dictionaries",
        produces = {"application/json"},
        method = RequestMethod.GET)
    ResponseEntity<AnalyticsParametersDto> analyticsDict();

}

