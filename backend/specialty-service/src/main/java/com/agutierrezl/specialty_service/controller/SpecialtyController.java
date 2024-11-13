package com.agutierrezl.specialty_service.controller;

import com.agutierrezl.specialty_service.dto.SpecialtyDTO;
import com.agutierrezl.specialty_service.service.ISpecialtyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/specialties")
public class SpecialtyController {

    private final ISpecialtyService specialtyService;

    @GetMapping("")
    public ResponseEntity<List<SpecialtyDTO>> getAll() {
        List<SpecialtyDTO> specialties = this.specialtyService.getAll();
        return new ResponseEntity<>(specialties, HttpStatus.OK);
    }

    @GetMapping("/{status}/status")
    public ResponseEntity<List<SpecialtyDTO>> getAllByStatus(@PathVariable Boolean status) {
        List<SpecialtyDTO> specialties = this.specialtyService.getAllByStatus(status);
        return new ResponseEntity<>(specialties, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecialtyDTO> getById(@PathVariable Long id) {
        SpecialtyDTO specialtyDTO = this.specialtyService.getById(id);
        return ResponseEntity.ok(specialtyDTO);
    }

    @PostMapping
    public ResponseEntity<SpecialtyDTO> save(@Valid @RequestBody SpecialtyDTO specialtyCreateDTO) {
        SpecialtyDTO specialtyDTO = this.specialtyService.save(specialtyCreateDTO);
        return new ResponseEntity<>(specialtyDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpecialtyDTO> update(@PathVariable Long id, @Valid @RequestBody SpecialtyDTO specialtyUpdateDTO) {
        SpecialtyDTO specialtyDTO = this.specialtyService.update(id, specialtyUpdateDTO);
        return new ResponseEntity<>(specialtyDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}/disable")
    public ResponseEntity<SpecialtyDTO> disable(@PathVariable Long id) {
        SpecialtyDTO specialtyDTO = this.specialtyService.disable(id);
        return new ResponseEntity<>(specialtyDTO, HttpStatus.OK);
    }

}
