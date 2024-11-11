package com.agutierrezl.specialty_service.controller;

import com.agutierrezl.specialty_service.dto.AvailabilityDTO;
import com.agutierrezl.specialty_service.service.IAvailabilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/availabilities")
public class AvailabilityController {

    private final IAvailabilityService availabilityService;

    @GetMapping("/{id}")
    public ResponseEntity<AvailabilityDTO> getById(@PathVariable Long id) {
        AvailabilityDTO availabilityDTO = availabilityService.getById(id);
        return ResponseEntity.ok(availabilityDTO);
    }
}
