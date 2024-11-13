package com.agutierrezl.specialty_service.validator.impl.availability;

import com.agutierrezl.specialty_service.dto.AvailabilityDTO;
import com.agutierrezl.specialty_service.exception.AvailabilityException;
import com.agutierrezl.specialty_service.repository.IAvailabilityRepository;
import com.agutierrezl.specialty_service.validator.IAvailabilityValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AvailabilityExistsValidatorImpl implements IAvailabilityValidator {

    private final IAvailabilityRepository availabilityRepository;

    @Override
    public void validate(Long id, AvailabilityDTO availabilityDTO) {
        checkIfAvailabilityExistsByDayAndStartTimeAndEndTimeAndSpecialtyId(id, availabilityDTO);
    }

    private void checkIfAvailabilityExistsByDayAndStartTimeAndEndTimeAndSpecialtyId(Long id, AvailabilityDTO availabilityDTO) {
        this.availabilityRepository.findByDayAndStartTimeAndEndTimeAndSpecialtyIdAndStatus(availabilityDTO.getDay(), availabilityDTO.getStartTime(),
                availabilityDTO.getEndTime(), availabilityDTO.getSpecialty().getId(), true).ifPresent(availabilty -> {
            if (!availabilty.getId().equals(id)) {
                throw new AvailabilityException("El Horario " +
                        "con especialidad '" + availabilty.getSpecialty().getName() +
                        ", dia '" + availabilty.getDay() +
                        ", hora de inicio '" + availabilty.getStartTime() +
                        " y hora de fin '" + availabilty.getEndTime() +
                        "' ya existe.", HttpStatus.BAD_REQUEST);
            }
        });
    }

}
