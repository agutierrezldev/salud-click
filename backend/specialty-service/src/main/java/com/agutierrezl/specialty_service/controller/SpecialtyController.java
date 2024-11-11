package com.agutierrezl.specialty_service.controller;

import com.agutierrezl.specialty_service.dto.AvailabilityDTO;
import com.agutierrezl.specialty_service.dto.SpecialtyDTO;
import com.agutierrezl.specialty_service.service.ISpecialtyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/specialties")
public class SpecialtyController {

    private final ISpecialtyService specialtyService;

    @GetMapping("")
    public ResponseEntity<List<SpecialtyDTO>> getAll() {
        List<SpecialtyDTO> specialties = specialtyService.getAll();
        return new ResponseEntity<>(specialties, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecialtyDTO> getById(@PathVariable Long id) {
        SpecialtyDTO specialtyDTO = specialtyService.getById(id);
        return ResponseEntity.ok(specialtyDTO);
    }

    @PostMapping
    public ResponseEntity<SpecialtyDTO> save(@Valid @RequestBody SpecialtyDTO specialtyCreateDTO) {
        SpecialtyDTO specialtyDTO = specialtyService.save(specialtyCreateDTO);
        return new ResponseEntity<>(specialtyDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpecialtyDTO> update(@PathVariable Long id, @Valid @RequestBody SpecialtyDTO specialtyUpdateDTO) {
        SpecialtyDTO specialtyDTO = specialtyService.update(id, specialtyUpdateDTO);
        return new ResponseEntity<>(specialtyDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}/disable")
    public ResponseEntity<SpecialtyDTO> disable(@PathVariable Long id) {
        SpecialtyDTO specialtyDTO = specialtyService.disable(id);
        return new ResponseEntity<>(specialtyDTO, HttpStatus.OK);
    }


//    @GetMapping("/{id}/availability")
//    public ResponseEntity<List<AvailabilityDTO>> getSpecialtyWithAvailability(@PathVariable Long id) {
//        List<AvailabilityDTO> availabilities = availabilityService.findBySpecialtyId(id)
//                .stream()
//                .map(availability ->availability.addLinks(id))
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(availabilities);
//    }
}
