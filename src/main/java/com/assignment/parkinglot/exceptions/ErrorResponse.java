package com.assignment.parkinglot.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

  private final int status;
  private final String message;
  private final String uuid;

  public ErrorResponse(int status, String message, String uuid) {
    this.status = status;
    this.message = message;
    this.uuid = uuid;
  }

  public int getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }

  public String getUuid() {
    return uuid;
  }
}
