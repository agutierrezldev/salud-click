package com.agutierrezl.specialty_service.service;

import com.agutierrezl.specialty_service.dto.AvailabilityDTO;

import java.util.List;

public interface IAvailabilityService {

    AvailabilityDTO getById(Long id);
    List<AvailabilityDTO> findBySpecialtyId(Long specialtyId);
}
