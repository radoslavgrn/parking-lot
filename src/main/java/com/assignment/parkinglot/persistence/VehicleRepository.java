package com.assignment.parkinglot.persistence;

import com.assignment.parkinglot.models.Vehicle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

}
