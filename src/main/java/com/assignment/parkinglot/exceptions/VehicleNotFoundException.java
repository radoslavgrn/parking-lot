package com.assignment.parkinglot.exceptions;

public class VehicleNotFoundException extends RuntimeException {

  public VehicleNotFoundException() {
    super("Vehicle type not found. \n");
  }
}
