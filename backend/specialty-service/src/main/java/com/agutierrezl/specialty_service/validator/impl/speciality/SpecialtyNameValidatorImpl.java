package com.agutierrezl.specialty_service.validator.impl.speciality;

import com.agutierrezl.specialty_service.dto.SpecialtyDTO;
import com.agutierrezl.specialty_service.entity.Specialty;
import com.agutierrezl.specialty_service.exception.SpecialtyException;
import com.agutierrezl.specialty_service.repository.ISpecialtyRepository;
import com.agutierrezl.specialty_service.validator.ISpecialtyValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpecialtyNameValidatorImpl implements ISpecialtyValidator {

    private final ISpecialtyRepository specialtyRepository;

    @Override
    public void validate(Long id, SpecialtyDTO specialtyDTO) {
        checkIfSpecialtyExistsByName(id, specialtyDTO);
    }

    private void checkIfSpecialtyExistsByName(Long id, SpecialtyDTO specialtyDTO) {
        this.specialtyRepository.findByNameAndStatus(specialtyDTO.getName(), true).ifPresent(specialty -> {
            if (!specialty.getId().equals(id)) {
                throw new SpecialtyException("La especialidad con nombre '" + specialtyDTO.getName() + "' ya existe.", HttpStatus.BAD_REQUEST);
            }
        });
    }
}
