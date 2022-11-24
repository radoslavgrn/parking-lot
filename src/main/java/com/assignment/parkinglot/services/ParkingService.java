package com.assignment.parkinglot.services;

import com.assignment.parkinglot.exceptions.CommonException;
import com.assignment.parkinglot.models.Parking;
import com.assignment.parkinglot.models.Vehicle;
import com.assignment.parkinglot.persistence.ParkingRepository;
import java.util.function.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("parkingService")
@Transactional(rollbackFor = {Exception.class})
public class ParkingService {

  private static final int PARK_MAX_SIZE_CAR = 50;
  private static final int PARK_MAX_SIZE_BUS = 10;

  private final ParkingRepository parkingRepository;

  public ParkingService(ParkingRepository parkingRepository) {
    this.parkingRepository = parkingRepository;
  }

  public Parking addParkingEntry(Parking entry) {
    if (isParkingFull(entry.getVehicle())) {
      throw new CommonException("Parking is full, please come again later. \n");
    }

    return parkingRepository.save(entry);
  }

  public Page<Parking> getEntries(boolean isArchived, Pageable pageable) {
    return parkingRepository.findAllByArchivedIs(isArchived, pageable);
  }

  private boolean isParkingFull(Vehicle vehicle) {
    Predicate<Parking> predicate = p -> !p.isArchived() && p.getVehicle().getType()
        .equalsIgnoreCase(vehicle.getType());

    int count =
        (int) parkingRepository.findAll()
            .parallelStream()
            .filter(predicate)
            .count();

    return "C".equalsIgnoreCase(vehicle.getType()) ? count > PARK_MAX_SIZE_CAR
        : count > PARK_MAX_SIZE_BUS;
  }


}
