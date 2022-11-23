package com.assignment.parkinglot.models;

import com.assignment.parkinglot.exceptions.VehicleNotFoundException;
import java.math.BigDecimal;
import java.util.Arrays;

public enum Vehicle {
  CAR("C", BigDecimal.ONE, BigDecimal.TEN),
  BUS("B", BigDecimal.valueOf(5), BigDecimal.valueOf(40));
  private final String type;
  private final BigDecimal hourlyPrice;
  private final BigDecimal dailyPrice;

  Vehicle(final String type, final BigDecimal hourlyPrice, final BigDecimal dailyPrice) {
    this.type = type;
    this.hourlyPrice = hourlyPrice;
    this.dailyPrice = dailyPrice;
  }

  public String getType() {
    return type;
  }

  public BigDecimal getHourlyPrice() {
    return hourlyPrice;
  }

  public BigDecimal getDailyPrice() {
    return dailyPrice;
  }

  public static Vehicle findByTypeRaw(String rawType) {
    return Arrays.stream(Vehicle.values())
        .filter(v -> v.getType().equalsIgnoreCase(rawType))
        .findFirst()
        .orElseThrow(VehicleNotFoundException::new);
  }
}
