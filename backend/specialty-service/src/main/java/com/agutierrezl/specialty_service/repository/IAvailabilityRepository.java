package com.agutierrezl.specialty_service.repository;

import com.agutierrezl.specialty_service.entity.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface IAvailabilityRepository extends JpaRepository<Availability, Long> {
    List<Availability> findByStatus(Boolean status);

    List<Availability> findBySpecialtyId(Long id);

    Optional<Availability>
    findByDayAndStartTimeAndEndTimeAndSpecialtyIdAndStatus(LocalDate day, LocalTime startTime, LocalTime endTime, Long specialtyId, Boolean status);

}
