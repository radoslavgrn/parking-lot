package com.assignment.parkinglot.api.dto;

import com.assignment.parkinglot.models.Parking;
import com.assignment.parkinglot.models.Vehicle;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.TimeUnit;

public final class ParkingDtoMapper {

  private ParkingDtoMapper() {
  }


  public static Parking toParking(ParkingEntry entry) {
    Vehicle vehicleType = Vehicle.findByTypeRaw(entry.getVehicleType());

    LocalDateTime entryDate = LocalDateTime.now();

    LocalDateTime leaveDate = entry.isDaily()
        ? entryDate.plus(24, ChronoUnit.HOURS)
        : entryDate.plus(1, ChronoUnit.HOURS);

    return new Parking(entryDate, leaveDate, entry.isDaily(), false, vehicleType, entry.getHours());
  }
}
