package com.assignment.parkinglot.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "sales")
public class Sales {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Temporal(TemporalType.DATE)
  private Date entryDate;

  @Column(name = "amount", nullable = false)
  private BigDecimal amount;

  @OneToMany(mappedBy = "sales", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Parking> parking = new ArrayList<>();

  public Long getId() {
    return id;
  }

  public Sales setId(Long id) {
    this.id = id;
    return this;
  }

  public Sales(Long id, Date entryDate, BigDecimal amount, List<Parking> parking) {
    this.id = id;
    this.entryDate = entryDate;
    this.amount = amount;
    this.parking = parking;
  }

  public Sales() {
  }

  public List<Parking> getParking() {
    return parking;
  }

  public Sales setParking(List<Parking> parking) {
    this.parking = parking;
    return this;
  }

  public Date getEntryDate() {
    return entryDate;
  }

  public Sales setEntryDate(Date entryDate) {
    this.entryDate = entryDate;
    return this;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public Sales setAmount(BigDecimal amount) {
    this.amount = amount;
    return this;
  }
}
