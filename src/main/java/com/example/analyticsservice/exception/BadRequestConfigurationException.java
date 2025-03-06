package com.example.analyticsservice.exception;

import org.springframework.http.*;

public class BadRequestConfigurationException extends DefaultException {

    private static final int ERROR_BAD_CONFIGURATION_CODE = 4006;

    private static final String ERROR_BAD_CONFIGURATION =
        "Ошибка запроса, невозможная конфигурация данных, для следующих параметров: ";

    private static final HttpStatus ERROR_BAD_CONFIGURATION_STATUS = HttpStatus.BAD_REQUEST;

    public BadRequestConfigurationException(
        String message
    ) {
        super(
            ERROR_BAD_CONFIGURATION + message,
            ERROR_BAD_CONFIGURATION_CODE,
            ERROR_BAD_CONFIGURATION_STATUS);
    }

    public BadRequestConfigurationException(
        String message,
        Throwable cause
    ) {
        super(
            ERROR_BAD_CONFIGURATION + message,
            ERROR_BAD_CONFIGURATION_CODE,
            ERROR_BAD_CONFIGURATION_STATUS,
            cause);
    }

    public BadRequestConfigurationException() {
        super(
            ERROR_BAD_CONFIGURATION,
            ERROR_BAD_CONFIGURATION_CODE,
            ERROR_BAD_CONFIGURATION_STATUS);
    }

}
