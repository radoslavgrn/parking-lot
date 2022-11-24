package com.assignment.parkinglot.api.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class ParkingEntry {

  private Boolean isDaily;

  @Positive(message = "Hours should be positive number. \n")
  private Integer hours;

  @NotBlank(message = "Vehicle type is mandatory. \n")
  private String vehicleType;

  public ParkingEntry() {
  }

  public ParkingEntry(Boolean isDaily, Integer hours, String vehicleType) {
    this.isDaily = isDaily;
    this.hours = hours;
    this.vehicleType = vehicleType;
  }

  public ParkingEntry(Boolean isDaily, String vehicleType) {
    this.isDaily = isDaily;
    this.vehicleType = vehicleType;
  }

  public Boolean isDaily() {
    return isDaily;
  }

  public String getVehicleType() {
    return vehicleType;
  }

  public Integer getHours() {
    return hours;
  }

  public ParkingEntry setDaily(Boolean daily) {
    isDaily = daily;
    return this;
  }

  public ParkingEntry setHours(Integer hours) {
    this.hours = hours;
    return this;
  }

  public ParkingEntry setVehicleType(String vehicleType) {
    this.vehicleType = vehicleType;
    return this;
  }
}
