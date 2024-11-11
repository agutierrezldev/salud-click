package com.agutierrezl.specialty_service.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Specialty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Boolean status;

    @OneToMany(mappedBy = "specialty", cascade = CascadeType.ALL)
    private List<Availability> availabilities;
}
