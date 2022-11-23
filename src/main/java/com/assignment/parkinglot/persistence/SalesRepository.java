package com.assignment.parkinglot.persistence;

import com.assignment.parkinglot.models.Sales;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository<Sales, Long> {

  Page<Sales> findAll(Pageable pageable);
}
