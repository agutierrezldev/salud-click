package com.agutierrezl.specialty_service.repository;

import com.agutierrezl.specialty_service.entity.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAvailabilityRepository extends JpaRepository<Availability,Long> {
    List<Availability> findBySpecialtyId(Long id);
}
