package com.assignment.parkinglot.exceptions;

import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonControllerAdvice {

  @ExceptionHandler(CommonException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<ErrorResponse> handleCommonException(
      CommonException ex
  ) {
    return ResponseEntity
        .badRequest()
        .body(buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
  }

  @ExceptionHandler(VehicleNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<ErrorResponse> handleVehicleNotFoundException(
      VehicleNotFoundException ex
  ) {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value()));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException ex
  ) {
    return ResponseEntity
        .badRequest()
        .body(buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
  }

  private ErrorResponse buildErrorResponse(String message, int httpCode) {
    return new ErrorResponse(httpCode, message, UUID.randomUUID().toString());
  }
}
