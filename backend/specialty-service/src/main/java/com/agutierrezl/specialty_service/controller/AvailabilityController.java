package com.agutierrezl.specialty_service.controller;

import com.agutierrezl.specialty_service.dto.AvailabilityDTO;
import com.agutierrezl.specialty_service.service.IAvailabilityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/availabilities")
public class AvailabilityController {

    private final IAvailabilityService availabilityService;

    @GetMapping("")
    public ResponseEntity<List<AvailabilityDTO>> getAll() {
        List<AvailabilityDTO> availabilities = this.availabilityService.getAll();
        return new ResponseEntity<>(availabilities, HttpStatus.OK);
    }

    @GetMapping("/{status}/status")
    public ResponseEntity<List<AvailabilityDTO>> getAllByStatus(@PathVariable Boolean status) {
        List<AvailabilityDTO> availabilities = this.availabilityService.getAllByStatus(status);
        return new ResponseEntity<>(availabilities, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvailabilityDTO> getById(@PathVariable Long id) {
        AvailabilityDTO availabilityDTO = this.availabilityService.getById(id);
        return ResponseEntity.ok(availabilityDTO);
    }

    @PostMapping
    public ResponseEntity<AvailabilityDTO> save(@Valid @RequestBody AvailabilityDTO availabilityCreateDTO) {
        AvailabilityDTO availabilityDTO = this.availabilityService.save(availabilityCreateDTO);
        return new ResponseEntity<>(availabilityDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AvailabilityDTO> update(@PathVariable Long id, @Valid @RequestBody AvailabilityDTO availabilityCreateDTO) {
        AvailabilityDTO availabilityDTO = this.availabilityService.update(id, availabilityCreateDTO);
        return new ResponseEntity<>(availabilityDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}/disable")
    public ResponseEntity<AvailabilityDTO> disable(@PathVariable Long id) {
        AvailabilityDTO availabilityDTO = this.availabilityService.disable(id);
        return new ResponseEntity<>(availabilityDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}/specialty")
    public ResponseEntity<List<AvailabilityDTO>> getAvailabilitiesBySpecialId(@PathVariable Long id) {
        List<AvailabilityDTO> availabilities = this.availabilityService.getAvailabilitiesBySpecialId(id)
                .stream()
                .map(availability ->availability.addLinks(id))
                .collect(Collectors.toList());
        return ResponseEntity.ok(availabilities);
    }

}
