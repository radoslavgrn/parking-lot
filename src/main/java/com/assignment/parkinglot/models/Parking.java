package com.assignment.parkinglot.models;

import java.time.LocalDateTime;
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

@Entity
@Table(name = "parking")
public class Parking {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "entry_date", nullable = false)
  private LocalDateTime entryDate;

  @Column(name = "leave_date", nullable = false)
  private LocalDateTime leaveDate;

  @Column(name = "is_daily", nullable = false, columnDefinition = "TINYINT", length = 1)
  private boolean isDaily = false;

  @Column(name = "is_archived", nullable = false, columnDefinition = "TINYINT", length = 1)
  private boolean isArchived = false;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  @JoinColumn(name = "sales_id")
  private Sales sales;

  @Enumerated(EnumType.STRING)
  private Vehicle vehicle;

  @Column(name = "hours")
  private Integer hours;

  public Long getId() {
    return id;
  }

  public Parking setId(Long id) {
    this.id = id;
    return this;
  }

  public Parking(LocalDateTime entryDate, LocalDateTime leaveDate, boolean isDaily,
      boolean isArchived, Vehicle vehicle, int hours) {
    this.entryDate = entryDate;
    this.leaveDate = leaveDate;
    this.isDaily = isDaily;
    this.isArchived = isArchived;
    this.vehicle = vehicle;
    this.hours = hours;
  }

  public Parking() {
  }

  public LocalDateTime getEntryDate() {
    return entryDate;
  }

  public Parking setEntryDate(LocalDateTime entryDate) {
    this.entryDate = entryDate;
    return this;
  }

  public LocalDateTime getLeaveDate() {
    return leaveDate;
  }

  public Parking setLeaveDate(LocalDateTime leaveDate) {
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

  public Integer getHours() {
    return hours;
  }

  public Parking setHours(Integer hours) {
    this.hours = hours;
    return this;
  }
}
