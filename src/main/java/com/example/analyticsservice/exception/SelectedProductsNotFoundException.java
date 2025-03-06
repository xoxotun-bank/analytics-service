package com.example.analyticsservice.exception;

import org.springframework.http.*;

public class SelectedProductsNotFoundException extends DefaultException {

    private static final int SELECTED_PRODUCTS_NOT_FOUND_API_CODE = 4003;

    private static final String SELECTED_PRODUCTS_NOT_FOUND_MESSAGE =
        "Не удалось найти данные для составления аналитического отчёта по указанным параметрам";

    private static final HttpStatus SELECTED_PRODUCTS_NOT_FOUND_STATUS = HttpStatus.NOT_FOUND;

    public SelectedProductsNotFoundException() {
        super(
            SELECTED_PRODUCTS_NOT_FOUND_MESSAGE,
            SELECTED_PRODUCTS_NOT_FOUND_API_CODE,
            SELECTED_PRODUCTS_NOT_FOUND_STATUS);
    }

    public SelectedProductsNotFoundException(
        Throwable cause
    ) {
        super(SELECTED_PRODUCTS_NOT_FOUND_MESSAGE,
            SELECTED_PRODUCTS_NOT_FOUND_API_CODE,
            SELECTED_PRODUCTS_NOT_FOUND_STATUS, cause);
    }

}
