package com.agutierrezl.specialty_service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class SpecialtyException extends RuntimeException {

    private final HttpStatus status;

    public SpecialtyException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public SpecialtyException(String message, Throwable cause, HttpStatus status) {
        super(message, cause);
        this.status = status;
    }

}
