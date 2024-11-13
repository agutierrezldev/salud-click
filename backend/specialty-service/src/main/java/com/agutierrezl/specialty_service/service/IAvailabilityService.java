package com.agutierrezl.specialty_service.service;

import com.agutierrezl.specialty_service.dto.AvailabilityDTO;

import java.util.List;

public interface IAvailabilityService {

    List<AvailabilityDTO> getAllByStatus(Boolean status);

    List<AvailabilityDTO> getAll();

    AvailabilityDTO getById(Long id);

    AvailabilityDTO save(AvailabilityDTO availabilityDTO);

    AvailabilityDTO update(Long id, AvailabilityDTO availabilityDTO);

    AvailabilityDTO disable(Long id);

    List<AvailabilityDTO> findBySpecialtyId(Long specialtyId);

    List<AvailabilityDTO> getAvailabilitiesBySpecialId(Long id);
}
