package com.assignment.parkinglot.api.dto;

import com.assignment.parkinglot.models.Parking;
import com.assignment.parkinglot.models.Vehicle;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import org.springframework.util.ObjectUtils;

public final class ParkingDtoMapper {

  private ParkingDtoMapper() {
  }


  public static Parking toParking(ParkingEntry entry) {
    Vehicle vehicleType = Vehicle.findByTypeRaw(entry.getVehicleType());

    LocalDateTime entryDate = LocalDateTime.now();

    int hours = ObjectUtils.isEmpty(entry.getHours()) ? 0 : entry.getHours();

    LocalDateTime leaveDate = Boolean.TRUE.equals(entry.isDaily())
        ? entryDate.plus(24, ChronoUnit.HOURS)
        : entryDate.plus(hours, ChronoUnit.HOURS);

    return new Parking(entryDate, leaveDate, entry.isDaily(), false, vehicleType, entry.getHours());
  }
}
