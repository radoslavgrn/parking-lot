package com.assignment.parkinglot.persistence;

import com.assignment.parkinglot.models.Parking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingRepository extends JpaRepository<Parking, Long> {

  Page<Parking> findAllByArchivedIs(boolean isArchived, Pageable pageable);
}
