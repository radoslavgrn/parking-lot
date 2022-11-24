package com.assignment.parkinglot.api;

import static com.assignment.parkinglot.api.ParkingLotController.ENTRIES_API;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.assignment.parkinglot.api.dto.ParkingEntry;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class ParkingLotControllerTest {

  @Autowired
  protected MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void addParkingEntry() throws Exception {

    ParkingEntry entry = new ParkingEntry(false, 3, "C");

    mockMvc.perform(post(ENTRIES_API)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(entry))
        )
        .andExpect(status().isCreated());

  }

  @Test
  void getEntries() {
  }

  @Test
  void getSales() {
  }
}