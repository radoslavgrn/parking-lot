package com.assignment.parkinglot.models;

import java.sql.Timestamp;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "parking")
public class Parking {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Temporal(TemporalType.TIMESTAMP)
  private Timestamp entryDate;

  @Temporal(TemporalType.TIMESTAMP)
  private Timestamp leaveDate;

  @Column(name = "is_monthly", nullable = false, columnDefinition = "TINYINT", length = 1)
  private boolean isDaily = false;

  @Column(name = "is_archived", nullable = false, columnDefinition = "TINYINT", length = 1)
  private boolean isArchived = false;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  @JoinColumn(name = "sales_id", nullable = false)
  private Sales sales;

  @Enumerated(EnumType.STRING)
  private Vehicle vehicle;

  public Long getId() {
    return id;
  }

  public Parking setId(Long id) {
    this.id = id;
    return this;
  }

  public Parking(Timestamp entryDate, Timestamp leaveDate, boolean isDaily,
      boolean isArchived, Vehicle vehicle) {
    this.entryDate = entryDate;
    this.leaveDate = leaveDate;
    this.isDaily = isDaily;
    this.isArchived = isArchived;
    this.vehicle = vehicle;
  }

  public Parking() {
  }

  public Timestamp getEntryDate() {
    return entryDate;
  }

  public Parking setEntryDate(Timestamp entryDate) {
    this.entryDate = entryDate;
    return this;
  }

  public Timestamp getLeaveDate() {
    return leaveDate;
  }

  public Parking setLeaveDate(Timestamp leaveDate) {
    this.leaveDate = leaveDate;
    return this;
  }

  public Sales getSales() {
    return sales;
  }

  public Parking setSales(Sales sales) {
    this.sales = sales;
    return this;
  }

  public Parking setDaily(boolean daily) {
    isDaily = daily;
    return this;
  }

  public boolean isArchived() {
    return isArchived;
  }

  public Parking setArchived(boolean archived) {
    isArchived = archived;
    return this;
  }

  public Vehicle getVehicle() {
    return vehicle;
  }

  public Parking setVehicle(Vehicle vehicle) {
    this.vehicle = vehicle;
    return this;
  }

  public boolean isDaily() {
    return isDaily;
  }
}
