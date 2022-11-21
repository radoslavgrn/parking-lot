package com.assignment.parkinglot.persistence;

import com.assignment.parkinglot.models.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface SalesRepository extends JpaRepository<Sales, Long> {

}
