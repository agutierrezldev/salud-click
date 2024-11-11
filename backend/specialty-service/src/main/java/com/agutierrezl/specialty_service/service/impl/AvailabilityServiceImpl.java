package com.agutierrezl.specialty_service.service.impl;

import com.agutierrezl.specialty_service.dto.AvailabilityDTO;
import com.agutierrezl.specialty_service.entity.Availability;
import com.agutierrezl.specialty_service.repository.IAvailabilityRepository;
import com.agutierrezl.specialty_service.service.IAvailabilityService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AvailabilityServiceImpl implements IAvailabilityService {

    private final IAvailabilityRepository availabilityRepository;
    private final ModelMapper modelMapper;

    @Override
    public AvailabilityDTO getById(Long id) {
        Optional<Availability> availabilityOptional = availabilityRepository.findById(id);

        if (availabilityOptional.isPresent()) {
            return convertToDTO(availabilityOptional.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Availability not found");
        }
    }

    @Override
    public List<AvailabilityDTO> findBySpecialtyId(Long specialtyId) {
        List<Availability> availabilities = availabilityRepository.findBySpecialtyId(specialtyId);
        return availabilities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public AvailabilityDTO convertToDTO(Availability availability){
        return modelMapper.map(availability, AvailabilityDTO.class);
    }
}
