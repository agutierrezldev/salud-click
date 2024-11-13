package com.agutierrezl.specialty_service.service.impl;

import com.agutierrezl.specialty_service.dto.AvailabilityDTO;
import com.agutierrezl.specialty_service.entity.Availability;
import com.agutierrezl.specialty_service.exception.AvailabilityException;
import com.agutierrezl.specialty_service.repository.IAvailabilityRepository;
import com.agutierrezl.specialty_service.service.IAvailabilityService;
import com.agutierrezl.specialty_service.service.ISpecialtyService;
import com.agutierrezl.specialty_service.validator.IAvailabilityValidator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AvailabilityServiceImpl implements IAvailabilityService {

    private final IAvailabilityRepository availabilityRepository;
    private final ModelMapper modelMapper;
    private final List<IAvailabilityValidator> availabilityValidators;

    private final ISpecialtyService specialtyService;

    @Override
    public List<AvailabilityDTO> getAllByStatus(Boolean status) {
        List<Availability> availabilities = this.availabilityRepository.findByStatus(status);
        return availabilities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AvailabilityDTO> getAll() {
        List<Availability> availabilities = this.availabilityRepository.findAll();
        return availabilities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AvailabilityDTO getById(Long id) {
        return convertToDTO(this.getAvailabilityById(id));
    }

    @Override
    public AvailabilityDTO save(AvailabilityDTO availabilityDTO) {
        availabilityValidators.forEach(availability -> availability.validate(null, availabilityDTO));
        availabilityDTO.setSpecialty(this.specialtyService.getById(availabilityDTO.getSpecialty().getId()));
        Availability availability = this.availabilityRepository.save(convertToEntity(availabilityDTO));
        return convertToDTO(availability);
    }

    @Override
    public AvailabilityDTO update(Long id, AvailabilityDTO availabilityDTO) {
        availabilityValidators.forEach(availability -> availability.validate(id, availabilityDTO));
        Availability availabilityUpdate = this.getAvailabilityById(id);
        availabilityDTO.setId(availabilityUpdate.getId());
        availabilityDTO.setSpecialty(this.specialtyService.getById(availabilityDTO.getSpecialty().getId()));
        Availability availability = this.availabilityRepository.save(convertToEntity(availabilityDTO));
        return convertToDTO(availability);
    }

    @Override
    public AvailabilityDTO disable(Long id) {
        Availability availability= this.getAvailabilityById(id);
        availability.setStatus(false);
        return convertToDTO(this.availabilityRepository.save(availability));
    }

    @Override
    public List<AvailabilityDTO> findBySpecialtyId(Long specialtyId) {
        List<Availability> availabilities = this.availabilityRepository.findBySpecialtyId(specialtyId);
        return availabilities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AvailabilityDTO> getAvailabilitiesBySpecialId(Long id) {
        this.specialtyService.getById(id);

        List<Availability> availabilities = this.availabilityRepository.findBySpecialtyId(id);
        return availabilities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private Availability getAvailabilityById(Long id) {
        Optional<Availability> availability = this.availabilityRepository.findById(id);
        if (availability.isPresent()) {
            return availability.get();
        } else {
            throw new AvailabilityException("Availability not found", HttpStatus.NOT_FOUND);
        }
    }

    public AvailabilityDTO convertToDTO(Availability availability) {
        return modelMapper.map(availability, AvailabilityDTO.class).addLinks(availability.getSpecialty().getId());
    }

    public Availability convertToEntity(AvailabilityDTO availabilityDTO) {
        return modelMapper.map(availabilityDTO, Availability.class);
    }
}
