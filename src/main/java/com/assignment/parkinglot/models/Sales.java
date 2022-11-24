package com.assignment.parkinglot.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sales")
public class Sales {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "entry_date", nullable = false)
  private LocalDateTime entryDate;

  @Column(name = "amount", nullable = false)
  private BigDecimal amount;

  @OneToOne(mappedBy = "sales", cascade = CascadeType.ALL, orphanRemoval = true)
  private Parking parking;

  public Long getId() {
    return id;
  }

  public Sales setId(Long id) {
    this.id = id;
    return this;
  }

  public Sales(LocalDateTime entryDate, BigDecimal amount) {
    this.entryDate = entryDate;
    this.amount = amount;
  }

  public Sales() {
  }

  public Parking getParking() {
    return parking;
  }

  public Sales setParking(Parking parking) {
    this.parking = parking;
    return this;
  }

  public LocalDateTime getEntryDate() {
    return entryDate;
  }

  public Sales setEntryDate(LocalDateTime entryDate) {
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
