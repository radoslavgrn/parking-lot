package com.assignment.parkinglot.api;

import static com.assignment.parkinglot.api.ParkingLotController.ENTRIES_API;
import static com.assignment.parkinglot.api.ParkingLotController.SALES_API;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.assignment.parkinglot.api.dto.ParkingEntry;
import com.assignment.parkinglot.models.Vehicle;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@Transactional
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ParkingLotControllerTest {

  private MockMvc mockMvc;

  @Autowired
  private WebApplicationContext context;

  @BeforeEach
  void setup() {
    mockMvc = MockMvcBuilders.webAppContextSetup(context)
        .alwaysDo(print())
        .build();
  }

  @Test
  @Order(1)
  void add_entry_car_must_return_201() throws Exception {

    ParkingEntry entry = new ParkingEntry(Boolean.FALSE, 3, "C");

    mockMvc.perform(post(ENTRIES_API)
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(entry))
        )
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.*").exists())
        .andExpect(jsonPath("$.*").isNotEmpty())
        .andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.vehicle").value(Vehicle.CAR.toString()))
        .andExpect(jsonPath("$.hours").value(3))
        .andExpect(jsonPath("$.daily").value(Boolean.FALSE.toString()))
        .andExpect(jsonPath("$.archived").value(Boolean.FALSE.toString()));
  }

  // further analysis
  @Test
  @Order(2)
  @Disabled
  void add_entry_bus_must_return_201() throws Exception {

    ParkingEntry entry = new ParkingEntry(Boolean.TRUE,  "B");

    mockMvc.perform(post(ENTRIES_API)
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(entry))
        )
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.*").exists())
        .andExpect(jsonPath("$.*").isNotEmpty())
        .andExpect(jsonPath("$.id").value(2))
        .andExpect(jsonPath("$.vehicle").value(Vehicle.BUS.toString()))
        .andExpect(jsonPath("$.hours").value("null"))
        .andExpect(jsonPath("$.daily").value(Boolean.TRUE.toString()))
        .andExpect(jsonPath("$.archived").value(Boolean.FALSE.toString()));
  }


  @Test
  @Sql(value = "classpath:entry-insert.sql", executionPhase = BEFORE_TEST_METHOD)
  @Order(3)
  void get_entries_must_return_200() throws Exception {
    mockMvc.perform(get(ENTRIES_API)
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.*").exists())
        .andExpect(jsonPath("$.content").isNotEmpty())
        .andExpect(jsonPath("$.content").isArray())
        .andExpect(jsonPath("$.content.[0].id").value(3))
        .andExpect(jsonPath("$.content.[1].id").value(4))
        .andExpect(jsonPath("$.content.[0].vehicle").value(Vehicle.CAR.toString()))
        .andExpect(jsonPath("$.content.[1].vehicle").value(Vehicle.BUS.toString()))
        .andExpect(jsonPath("$.content.[0].daily").value(Boolean.TRUE.toString()))
        .andExpect(jsonPath("$.content.[1].daily").value(Boolean.TRUE.toString()))
        .andExpect(jsonPath("$.content.[0].archived").value(Boolean.FALSE.toString()))
        .andExpect(jsonPath("$.content.[1].archived").value(Boolean.FALSE.toString()));
  }

  @Test
  @Order(4)
  void get_entries_return_no_content() throws Exception {
    mockMvc.perform(get(ENTRIES_API)
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.*").exists())
        .andExpect(jsonPath("$.content").isArray())
        .andExpect(jsonPath("$.content").isEmpty());
  }

  @Test
  @Sql(value = "classpath:sales-insert.sql", executionPhase = BEFORE_TEST_METHOD)
  @Order(5)
  void get_sales_must_return_200() throws Exception {
    mockMvc.perform(get(SALES_API)
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.*").exists())
        .andExpect(jsonPath("$.content").isArray())
        .andExpect(jsonPath("$.content").isNotEmpty());
  }

  @Test
  @Order(6)
  void add_entry_car_must_return_400() throws Exception {

    ParkingEntry entry = new ParkingEntry(Boolean.TRUE, 3, "C");

    mockMvc.perform(post(ENTRIES_API)
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(entry))
        )
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.*").exists())
        .andExpect(jsonPath("$.*").isNotEmpty())
        .andExpect(jsonPath("$.status").value(400))
        .andExpect(jsonPath("$.message").value("When daily chosen, cannot set hours. \n"))
        .andExpect(jsonPath("$.uuid").exists());
  }

  // further analysis
  @Test
  @Order(7)
  @Disabled
  void add_entry_bus_must_return_400() throws Exception {

    ParkingEntry entry = new ParkingEntry(Boolean.FALSE, "B");

    mockMvc.perform(post(ENTRIES_API)
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(entry))
        )
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.*").exists())
        .andExpect(jsonPath("$.*").isNotEmpty())
        .andExpect(jsonPath("$.status").value(400))
        .andExpect(jsonPath("$.message").value("Either choose daily or set hours. \n"))
        .andExpect(jsonPath("$.uuid").exists());
  }

  public static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}