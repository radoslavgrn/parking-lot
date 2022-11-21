package com.assignment.parkinglot.models;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "vehicles")
public class Vehicle {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "plate", length = 30, nullable = false)
  private String plate;

  @Column(name = "model", length = 30, nullable = false)
  private String model;

  @Enumerated(EnumType.STRING)
  private VehicleType vehicleType;

  @Temporal(TemporalType.DATE)
  private Date dateOfManufacture;

//  @ManyToOne
//  @JoinColumn(name ="vehicle_id", nullable = false)
//  private ParkingEntry parkingEntry;

  public Long getId() {
    return id;
  }

  public Vehicle setId(Long id) {
    this.id = id;
    return this;
  }

  public String getPlate() {
    return plate;
  }

  public Vehicle setPlate(String plate) {
    this.plate = plate;
    return this;
  }

  public VehicleType getVehicleType() {
    return vehicleType;
  }

  public Vehicle setVehicleType(VehicleType vehicleType) {
    this.vehicleType = vehicleType;
    return this;
  }

  public Date getDateOfManufacture() {
    return dateOfManufacture;
  }

  public Vehicle setDateOfManufacture(Date dateOfManufacture) {
    this.dateOfManufacture = dateOfManufacture;
    return this;
  }
}
