package com.agutierrezl.specialty_service.dto;

import com.agutierrezl.specialty_service.controller.AvailabilityController;
import com.agutierrezl.specialty_service.controller.SpecialtyController;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AvailabilityDTO extends RepresentationModel<AvailabilityDTO> {
    private Long id;
    private LocalDate day;
    private LocalTime startTime;
    private LocalTime endTime;

    public AvailabilityDTO addLinks(Long specialtyId) {

        this.add(WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(AvailabilityController.class).getById(id))
                .withSelfRel());

        this.add(WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(SpecialtyController.class).getById(specialtyId))
                .withRel("specialty"));

        return this;
    }
}
