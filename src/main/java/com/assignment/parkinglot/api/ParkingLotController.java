package com.assignment.parkinglot.api;

import com.assignment.parkinglot.api.dto.ParkingDtoMapper;
import com.assignment.parkinglot.api.dto.ParkingEntry;
import com.assignment.parkinglot.models.Parking;
import com.assignment.parkinglot.models.Sales;
import com.assignment.parkinglot.services.ParkingService;
import com.assignment.parkinglot.services.SalesService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParkingLotController {

  public static final String ENTRIES_API = "/api/v1/parking-lot/entries";
  public static final String SALES_API = "/api/v1/parking-lot/sales";
  private final ParkingService parkingService;
  private final SalesService salesService;

  public ParkingLotController(ParkingService parkingService, SalesService salesService) {
    this.parkingService = parkingService;
    this.salesService = salesService;
  }

  @PostMapping(value = ENTRIES_API, consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public Parking addParkingEntry(@RequestBody ParkingEntry entry
  ) {
    return parkingService.addParkingEntry(ParkingDtoMapper.toParking(entry));
  }

  @GetMapping(value = ENTRIES_API, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public Page<Parking> getEntries(
      @RequestParam(defaultValue = "false", required = false) boolean isArchived,
      @PageableDefault(sort = "entryDate") Pageable pageable) {
    return parkingService.getEntries(isArchived, pageable);
  }


  @GetMapping(value = SALES_API, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public Page<Sales> getSales(@PageableDefault(sort = "entryDate") Pageable pageable) {
    return salesService.getSales(pageable);
  }

}
