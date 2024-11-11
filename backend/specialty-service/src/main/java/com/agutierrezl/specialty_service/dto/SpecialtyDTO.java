package com.agutierrezl.specialty_service.dto;

import com.agutierrezl.specialty_service.controller.SpecialtyController;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

@Data
public class SpecialtyDTO extends RepresentationModel<SpecialtyDTO>{

    private Long id;

    @NotBlank
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String name;

    @Size(max = 150, message = "La descripción no puede exceder 150 caracteres")
    private String description;

    private Boolean status = true;

    public SpecialtyDTO addLinks(){
//        this.add(WebMvcLinkBuilder.linkTo(
//                        WebMvcLinkBuilder.methodOn(SpecialtyController.class).getSpecialtyWithAvailability(id))
//                .withRel("availability"));
        this.add(WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(SpecialtyController.class).getById(id))
                .withSelfRel());
        return this;
    }
}
