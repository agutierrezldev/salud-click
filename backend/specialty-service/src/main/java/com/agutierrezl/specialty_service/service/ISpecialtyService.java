package com.agutierrezl.specialty_service.service;

import com.agutierrezl.specialty_service.dto.SpecialtyDTO;
import java.util.List;

public interface ISpecialtyService {

    List<SpecialtyDTO> getAll();
    SpecialtyDTO getById(Long id);
    SpecialtyDTO save(SpecialtyDTO specialtyDTO);
    SpecialtyDTO update(Long id, SpecialtyDTO specialtyDTO);
    SpecialtyDTO disable(Long id);
}
