package com.agutierrezl.specialty_service.validator;

import com.agutierrezl.specialty_service.dto.AvailabilityDTO;

public interface IAvailabilityValidator {
    void validate(Long id, AvailabilityDTO availabilityDTO);
}
