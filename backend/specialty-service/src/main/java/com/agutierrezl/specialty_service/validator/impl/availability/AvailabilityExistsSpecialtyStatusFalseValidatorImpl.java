package com.agutierrezl.specialty_service.validator.impl.availability;

import com.agutierrezl.specialty_service.dto.AvailabilityDTO;
import com.agutierrezl.specialty_service.dto.SpecialtyDTO;
import com.agutierrezl.specialty_service.exception.SpecialtyException;
import com.agutierrezl.specialty_service.service.ISpecialtyService;
import com.agutierrezl.specialty_service.validator.IAvailabilityValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AvailabilityExistsSpecialtyStatusFalseValidatorImpl implements IAvailabilityValidator {

    private final ISpecialtyService specialtyService;

    @Override
    public void validate(Long id, AvailabilityDTO availabilityDTO) {
        checkIfSpecialtyExistsAndStatusFalse(availabilityDTO);
    }

    private void checkIfSpecialtyExistsAndStatusFalse(AvailabilityDTO availabilityDTO) {
        SpecialtyDTO specialtyDTO = this.specialtyService.getById(availabilityDTO.getSpecialty().getId());
        if (!specialtyDTO.getStatus()){
            throw new SpecialtyException("La especialidad con nombre '" + specialtyDTO.getName() + "' se encuentra inactiva.", HttpStatus.BAD_REQUEST);
        }
    }
}
