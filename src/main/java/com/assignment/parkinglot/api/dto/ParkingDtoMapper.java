package com.assignment.parkinglot.api.dto;

import com.assignment.parkinglot.models.Parking;
import com.assignment.parkinglot.models.Vehicle;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

public final class ParkingDtoMapper {

  private ParkingDtoMapper() {
  }


  public static Parking toParking(ParkingEntry entry) {
    Vehicle vehicleType = Vehicle.findByTypeRaw(entry.getVehicleType());

    Timestamp entryDate = new Timestamp(System.currentTimeMillis());

    Timestamp leaveDate = entry.isDaily()
        ? new Timestamp(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(1))
        : new Timestamp(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1));


    return new Parking(entryDate, leaveDate, entry.isDaily(), false, vehicleType);
  }
}
