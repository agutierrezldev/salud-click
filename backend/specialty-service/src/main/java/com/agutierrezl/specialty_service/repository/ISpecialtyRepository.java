package com.agutierrezl.specialty_service.repository;

import com.agutierrezl.specialty_service.entity.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ISpecialtyRepository extends JpaRepository<Specialty,Long> {
    Optional<Specialty> findByName(String name);
}
