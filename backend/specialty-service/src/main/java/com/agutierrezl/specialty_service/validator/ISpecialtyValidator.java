package com.agutierrezl.specialty_service.validator;

import com.agutierrezl.specialty_service.dto.SpecialtyDTO;

public interface ISpecialtyValidator {

    void validate(Long id, SpecialtyDTO specialtyDTO);
}
