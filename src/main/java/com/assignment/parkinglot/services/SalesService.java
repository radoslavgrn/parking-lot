package com.assignment.parkinglot.services;

import com.assignment.parkinglot.models.Parking;
import com.assignment.parkinglot.models.Sales;
import com.assignment.parkinglot.persistence.ParkingRepository;
import com.assignment.parkinglot.persistence.SalesRepository;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("salesService")
@Transactional(rollbackFor = {Exception.class})
public class SalesService {

  private final ParkingRepository parkingRepository;
  private final SalesRepository salesRepository;


  public SalesService(ParkingRepository parkingRepository, SalesRepository salesRepository) {
    this.parkingRepository = parkingRepository;
    this.salesRepository = salesRepository;
  }

  @Scheduled(fixedRate = 1, timeUnit = TimeUnit.HOURS)
  public void registerSale() {
    Predicate<Parking> predicate = p -> p.getLeaveDate()
        .after(new Timestamp(System.currentTimeMillis()));

    parkingRepository.findAll()
        .parallelStream()
        .filter(predicate)
        .forEach(this::registerSale);
  }

  public Page<Sales> getSales(Pageable pageable) {
    return salesRepository.findAll(pageable);
  }

  public void registerSale(Parking entry) {
    Sales newSale = salesRepository.save(new Sales(new Timestamp(System.currentTimeMillis()),
        entry.isDaily() ? entry.getVehicle().getDailyPrice()
            : entry.getVehicle().getHourlyPrice()));

    entry.setSales(newSale);

    parkingRepository.save(entry);
  }
}
