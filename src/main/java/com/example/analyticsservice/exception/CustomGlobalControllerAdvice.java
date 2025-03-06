package com.example.analyticsservice.exception;

import java.io.*;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.exc.*;
import feign.*;
import lombok.extern.slf4j.*;
import org.springframework.http.*;
import org.springframework.http.converter.*;
import org.springframework.security.access.*;
import org.springframework.validation.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;

import com.example.analyticsservice.swagger.server.dto.*;
import com.example.jwtstarter.exception.*;

@Slf4j
@ControllerAdvice
public class CustomGlobalControllerAdvice {

    private static final String ERROR_SERVER = "Внутренняя ошибка сервера или базы данных";

    private static final String ERROR_VALIDATION_NULL =
        "Ошибка запроса,  отсутствуют следующие параметры запроса:";

    private static final String ERROR_VALIDATION_PATTERN =
        "Ошибка запроса,  некорректные данные в следующих параметрах:";

    private static final String ERROR_FORBIDDEN =
        "Ошибка запроса, у пользователя нет доступа к данному ресурсу";

    private static final int ERROR_INVALID_TOKEN_CODE = 4001;

    private static final int ERROR_VALIDATION_NULL_CODE = 4002;

    private static final int ERROR_VALIDATION_PATTERN_CODE = 4004;

    private static final int ERROR_FORBIDDEN_CODE = 4005;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @ExceptionHandler(DefaultException.class)
    public ResponseEntity<ApiErrorResponseDto> handleDefaultException(DefaultException e) {
        log.error("Unexpected exception", e);
        ApiErrorResponseDto apiErrorResponseDto = new ApiErrorResponseDto();
        apiErrorResponseDto.setMessage(e.getMessage());
        apiErrorResponseDto.setCode(e.getApiCode());
        return ResponseEntity.status(e.getHttpStatus()).body(apiErrorResponseDto);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ApiErrorResponseDto> handleFeignException(FeignException ex) {

        try {
            if (ex instanceof FeignException.Unauthorized ||
                ex instanceof FeignException.BadRequest
            ) {
                var byteArray = ex.responseBody().get().array();
                var response = objectMapper.reader()
                    .readValue(byteArray, ApiErrorResponseDto.class);
                return ResponseEntity.status(ex.status()).body(response);
            }
        } catch (IOException e) {
            var runtimeException = new RuntimeException(ex);
            return handleRuntimeException(runtimeException);
        }

        var runtimeException = new RuntimeException(ex);
        return handleRuntimeException(runtimeException);
    }

    @ExceptionHandler(InvalidJwtTokenException.class)
    public ResponseEntity<ApiErrorResponseDto> handleInvalidJwtTokenException(
        InvalidJwtTokenException ex
    ) {
        ApiErrorResponseDto apiErrorResponseDto = new ApiErrorResponseDto();
        apiErrorResponseDto.setMessage(ex.getMessage());
        apiErrorResponseDto.setCode(ERROR_INVALID_TOKEN_CODE);
        return ResponseEntity.status(401).body(apiErrorResponseDto);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiErrorResponseDto> handleAccessDeniedException(AccessDeniedException e) {

        ApiErrorResponseDto apiErrorResponseDto = new ApiErrorResponseDto();
        HttpStatus status = HttpStatus.FORBIDDEN;
        apiErrorResponseDto.setMessage(ERROR_FORBIDDEN);
        apiErrorResponseDto.setCode(ERROR_FORBIDDEN_CODE);
        return ResponseEntity.status(status).body(apiErrorResponseDto);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiErrorResponseDto> handleRuntimeException(RuntimeException e) {
        log.error("Unexpected exception", e);
        ApiErrorResponseDto apiErrorResponseDto = new ApiErrorResponseDto();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        apiErrorResponseDto.setMessage(ERROR_SERVER);
        apiErrorResponseDto.setCode(status.value());
        return ResponseEntity.status(status).body(apiErrorResponseDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponseDto> handleMethodArgumentNotValidException(
        MethodArgumentNotValidException e
    ) {
        log.error("Unexpected exception", e);
        ApiErrorResponseDto apiErrorResponseDto = getValidationErrorResponse(e.getBindingResult());
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(apiErrorResponseDto);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorResponseDto> handleHttpMessageNotReadableException(
        HttpMessageNotReadableException e
    ) {
        log.error("Unexpected exception", e);

        if (e.getCause() instanceof InvalidFormatException ex) {
            return processInvalidFormatException(ex);
        }

        ApiErrorResponseDto apiErrorResponseDto = new ApiErrorResponseDto();
        apiErrorResponseDto.setMessage(ERROR_SERVER);

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(status).body(apiErrorResponseDto);
    }

    private ResponseEntity<ApiErrorResponseDto> processInvalidFormatException(
        InvalidFormatException ex
    ) {
        var field = ex.getPath().get(0).getFieldName();
        var value = ex.getValue();

        var apiErrorResponseDto = new ApiErrorResponseDto();
        apiErrorResponseDto.setCode(ERROR_VALIDATION_PATTERN_CODE);
        apiErrorResponseDto.setMessage(String.format(
            ERROR_VALIDATION_PATTERN + " %s - %s",
            field,
            value
        ));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErrorResponseDto);
    }

    private ApiErrorResponseDto getValidationErrorResponse(BindingResult bindingResult) {
        var stringBuilder = new StringBuilder();
        var errors = bindingResult.getFieldErrors();
        var nullFields = errors.stream().filter(e -> e.getCode().equals("NotNull")).toList();
        var apiErrorResponseDto = new ApiErrorResponseDto();
        if (!nullFields.isEmpty()) {
            apiErrorResponseDto.setCode(ERROR_VALIDATION_NULL_CODE);
            stringBuilder.append(ERROR_VALIDATION_NULL);
            nullFields.forEach(fieldError ->
                stringBuilder.append(String.format(" %s,", fieldError.getField()))
            );
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        } else {
            apiErrorResponseDto.setCode(ERROR_VALIDATION_PATTERN_CODE);
            var invalidFields = bindingResult.getFieldErrors()
                .stream()
                .filter(e -> e.getCode().equals("Pattern"))
                .toList();
            if (!invalidFields.isEmpty()) {
                stringBuilder.append(ERROR_VALIDATION_PATTERN);
                invalidFields.forEach(fieldError ->
                    stringBuilder.append(String.format(
                        " %s - %s,",
                        fieldError.getField(),
                        fieldError.getRejectedValue()))
                );
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            }
        }
        apiErrorResponseDto.setMessage(stringBuilder.toString());
        return apiErrorResponseDto;
    }

}
