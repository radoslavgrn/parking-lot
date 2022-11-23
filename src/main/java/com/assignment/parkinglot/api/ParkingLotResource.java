package com.assignment.parkinglot.api;

import com.assignment.parkinglot.api.dto.ParkingDtoMapper;
import com.assignment.parkinglot.api.dto.ParkingEntry;
import com.assignment.parkinglot.models.Parking;
import com.assignment.parkinglot.models.Sales;
import com.assignment.parkinglot.services.ParkingService;
import com.assignment.parkinglot.services.SalesService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/parking-lot")
public class ParkingLotResource {

  public static final String ENTRIES_API = "/entries";
  public static final String SALES_API = "/sales";
  private final ParkingService parkingService;
  private final SalesService salesService;

  public ParkingLotResource(ParkingService parkingService, SalesService salesService) {
    this.parkingService = parkingService;
    this.salesService = salesService;
  }

  @PostMapping(ENTRIES_API)
  @ResponseStatus(HttpStatus.CREATED)
  public Parking addParkingEntry(@RequestBody ParkingEntry entry
  ) {
    return parkingService.addParkingEntry(ParkingDtoMapper.toParking(entry));
  }

  @GetMapping(ENTRIES_API)
  @ResponseStatus(HttpStatus.OK)
  public Page<Parking> getEntries(
      @RequestParam(defaultValue = "false", required = false) boolean isArchived,
      Pageable pageable) {
    return parkingService.getEntries(isArchived, pageable);
  }


  @GetMapping(SALES_API)
  @ResponseStatus(HttpStatus.OK)
  public Page<Sales> getSales(Pageable pageable) {
    return salesService.getSales(pageable);
  }

}
