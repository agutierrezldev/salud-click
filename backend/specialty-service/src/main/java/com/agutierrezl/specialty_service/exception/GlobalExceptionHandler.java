package com.agutierrezl.specialty_service.exception;

import com.agutierrezl.specialty_service.exception.error.CustomError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SpecialtyException.class)
    public ResponseEntity<CustomError> handleSpecialtyException(SpecialtyException ex, WebRequest request) {
        return buildErrorResponse(ex.getMessage(), ex.getStatus(), request);
    }

    @ExceptionHandler(AvailabilityException.class)
    public ResponseEntity<CustomError> handleAvailabilityException(AvailabilityException ex, WebRequest request) {
        return buildErrorResponse(ex.getMessage(), ex.getStatus(), request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>("Ocurrió un error inesperado: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<CustomError> buildErrorResponse(String message, HttpStatus status, WebRequest request) {
        String path = request.getDescription(false).replace("uri", "");
        CustomError customError = new CustomError(LocalDateTime.now(), message, status.value(), status.getReasonPhrase(), path);
        return new ResponseEntity<>(customError, status);
    }

}
