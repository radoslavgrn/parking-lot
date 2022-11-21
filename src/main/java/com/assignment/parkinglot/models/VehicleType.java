package com.assignment.parkinglot.models;

import java.math.BigDecimal;

public enum VehicleType {
  CAR("C", BigDecimal.ONE, BigDecimal.TEN),
  BUS("B", BigDecimal.valueOf(5), BigDecimal.valueOf(40));
  private final String type;
  private final BigDecimal hourlyPrice;
  private final BigDecimal dailyPrice;

  VehicleType(final String type, final BigDecimal hourlyPrice, final BigDecimal dailyPrice) {
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
}
