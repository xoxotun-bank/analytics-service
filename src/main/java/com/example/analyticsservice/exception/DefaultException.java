package com.example.analyticsservice.exception;

import lombok.*;
import org.springframework.http.*;

@Data
public class DefaultException extends RuntimeException {

    private int apiCode;

    private HttpStatus httpStatus;

    public DefaultException(String message, int apiCode, HttpStatus httpStatus) {
        super(message);
        this.apiCode = apiCode;
        this.httpStatus = httpStatus;
    }

    public DefaultException(String message, int apiCode, HttpStatus httpStatus, Throwable cause) {
        super(message, cause);
        this.apiCode = apiCode;
        this.httpStatus = httpStatus;
    }

}
