package com.agutierrezl.specialty_service.validator.impl.availability;

import com.agutierrezl.specialty_service.dto.AvailabilityDTO;
import com.agutierrezl.specialty_service.service.ISpecialtyService;
import com.agutierrezl.specialty_service.validator.IAvailabilityValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AvailabilityExistsSpecialtyValidatorImpl implements IAvailabilityValidator {

    private final ISpecialtyService specialtyService;

    @Override
    public void validate(Long id, AvailabilityDTO availabilityDTO) {
        checkIfSpecialtyExists(availabilityDTO);
    }

    private void checkIfSpecialtyExists(AvailabilityDTO availabilityDTO) {
        this.specialtyService.getById(availabilityDTO.getSpecialty().getId());
    }
}
