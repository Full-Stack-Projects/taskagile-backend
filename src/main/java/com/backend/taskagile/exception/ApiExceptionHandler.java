package com.backend.taskagile.exception;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler(value = {MethodArgumentNotValidException.class})
  public ResponseEntity<Object> handleRequestException(MethodArgumentNotValidException e) {
    //Create payload containing exception details
    ErrorResponseDetail errorResponseDetail = new ErrorResponseDetail(
        "Input invalid values",
        HttpStatus.BAD_REQUEST,
        ZonedDateTime.now(ZoneId.of("Z"))
    );
    //Return response entity
    return new ResponseEntity<>(errorResponseDetail, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = {UserAlreadyExistsException.class})
  public ResponseEntity<Object> handleRequestException(UserAlreadyExistsException e) {
    //Create payload containing exception details
    ErrorResponseDetail errorResponseDetail = new ErrorResponseDetail(
        e.getMessage(),
        HttpStatus.CONFLICT,
        ZonedDateTime.now(ZoneId.of("Z"))
    );
    //Return response entity
    return new ResponseEntity<>(errorResponseDetail, HttpStatus.CONFLICT);
  }


}
