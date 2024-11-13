package com.agutierrezl.specialty_service.service.impl;

import com.agutierrezl.specialty_service.dto.AvailabilityDTO;
import com.agutierrezl.specialty_service.dto.SpecialtyDTO;
import com.agutierrezl.specialty_service.entity.Specialty;
import com.agutierrezl.specialty_service.exception.SpecialtyException;
import com.agutierrezl.specialty_service.repository.ISpecialtyRepository;
import com.agutierrezl.specialty_service.service.IAvailabilityService;
import com.agutierrezl.specialty_service.service.ISpecialtyService;
import com.agutierrezl.specialty_service.validator.ISpecialtyValidator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SpecialtyServiceImpl implements ISpecialtyService {

    private final ISpecialtyRepository specialtyRepository;
    private final ModelMapper modelMapper;
    private final List<ISpecialtyValidator> specialtyValidators;

    @Override
    public List<SpecialtyDTO> getAllByStatus(Boolean status) {
        List<Specialty> specialties = this.specialtyRepository.findByStatus(status);
        return specialties.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SpecialtyDTO> getAll() {
        List<Specialty> specialties = this.specialtyRepository.findAll();
        return specialties.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SpecialtyDTO getById(Long id) {
        return convertToDTO(this.getSpecialtyById(id));
    }

    @Override
    public SpecialtyDTO save(SpecialtyDTO specialtyDTO) {
        specialtyValidators.forEach(specialty -> specialty.validate(null, specialtyDTO));
        Specialty specialty = this.specialtyRepository.save(convertToEntity(specialtyDTO));
        return convertToDTO(specialty);
    }

    @Override
    public SpecialtyDTO update(Long id, SpecialtyDTO specialtyDTO) {
        Specialty specialtyUpdate = this.getSpecialtyById(id);
        specialtyValidators.forEach(specialty -> specialty.validate(specialtyUpdate.getId(), specialtyDTO));
        specialtyDTO.setId(specialtyUpdate.getId());
        Specialty specialty = this.specialtyRepository.save(convertToEntity(specialtyDTO));
        return convertToDTO(specialty);
    }

    @Override
    public SpecialtyDTO disable(Long id) {
        Specialty specialty = this.getSpecialtyById(id);
        specialty.setStatus(false);
        return convertToDTO(this.specialtyRepository.save(specialty));
    }

    private Specialty getSpecialtyById(Long id) {
        Optional<Specialty> specialty = this.specialtyRepository.findById(id);
        if (specialty.isPresent()) {
            return specialty.get();
        } else {
            throw new SpecialtyException("Specialty not found", HttpStatus.NOT_FOUND);
        }
    }


    public SpecialtyDTO convertToDTO(Specialty specialty) {
        return modelMapper.map(specialty, SpecialtyDTO.class).addLinks();
    }

    public Specialty convertToEntity(SpecialtyDTO specialtyDTO) {
        return modelMapper.map(specialtyDTO, Specialty.class);
    }
}
