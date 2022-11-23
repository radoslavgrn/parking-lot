package com.assignment.parkinglot.api.dto;

import javax.validation.constraints.NotBlank;

public class ParkingEntry {

  private final boolean isDaily;

  @NotBlank(message = "Vehicle type is mandatory. \n")
  private final String vehicleType;

  public ParkingEntry(boolean isDaily, String vehicleType) {
    this.isDaily = isDaily;
    this.vehicleType = vehicleType;
  }

  public boolean isDaily() {
    return isDaily;
  }

  public String getVehicleType() {
    return vehicleType;
  }
}
