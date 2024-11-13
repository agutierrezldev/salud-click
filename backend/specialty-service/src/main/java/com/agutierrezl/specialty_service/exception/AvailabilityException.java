package com.agutierrezl.specialty_service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AvailabilityException extends RuntimeException {

    private final HttpStatus status;

    public AvailabilityException(String message,HttpStatus status) {
        super(message);
        this.status = status;
    }

    public AvailabilityException(String message, Throwable cause, HttpStatus status) {
        super(message, cause);
        this.status = status;
    }
}
