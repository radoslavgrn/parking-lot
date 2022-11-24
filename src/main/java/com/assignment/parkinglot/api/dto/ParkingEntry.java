package com.assignment.parkinglot.api.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ParkingEntry {

  private final boolean isDaily;

  @NotNull(message = "Hours should be present. \n")
  @Positive(message = "Hours should be positive number. \n")
  private final Integer hours;

  @NotBlank(message = "Vehicle type is mandatory. \n")
  private final String vehicleType;

  public ParkingEntry(boolean isDaily, Integer hours, String vehicleType) {
    this.isDaily = isDaily;
    this.hours = hours;
    this.vehicleType = vehicleType;
  }

  public boolean isDaily() {
    return isDaily;
  }

  public String getVehicleType() {
    return vehicleType;
  }

  public Integer getHours() {
    return hours;
  }
}
