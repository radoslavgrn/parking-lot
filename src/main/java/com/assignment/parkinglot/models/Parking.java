package com.assignment.parkinglot.models;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "parking")
public class Parking {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Temporal(TemporalType.DATE)
  private Date entryDate;

  @Temporal(TemporalType.DATE)
  private Date leaveDate;

  @Column(name = "hourly", nullable = false, columnDefinition = "TINYINT", length = 1)
  private boolean isHourly = true;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  @JoinColumn(name = "sales_id", nullable = false)
  private Sales sales;

  public Long getId() {
    return id;
  }

  public Parking setId(Long id) {
    this.id = id;
    return this;
  }


  public Parking(Long id, Date entryDate, Date leaveDate, boolean isHourly, Sales sales) {
    this.id = id;
    this.entryDate = entryDate;
    this.leaveDate = leaveDate;
    this.isHourly = isHourly;
    this.sales = sales;
  }

  public Parking() {
  }

  public Date getEntryDate() {
    return entryDate;
  }

  public Parking setEntryDate(Date entryDate) {
    this.entryDate = entryDate;
    return this;
  }

  public Date getLeaveDate() {
    return leaveDate;
  }

  public Parking setLeaveDate(Date leaveDate) {
    this.leaveDate = leaveDate;
    return this;
  }

  public boolean isHourly() {
    return isHourly;
  }

  public Parking setHourly(boolean hourly) {
    isHourly = hourly;
    return this;
  }

  public Sales getSales() {
    return sales;
  }

  public Parking setSales(Sales sales) {
    this.sales = sales;
    return this;
  }
}
