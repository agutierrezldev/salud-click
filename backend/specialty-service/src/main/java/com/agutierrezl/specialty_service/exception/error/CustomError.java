package com.agutierrezl.specialty_service.exception.error;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CustomError {
    private LocalDateTime timestamp;
    private String message;
    private int status;
    private String error;
    private String path;
}
