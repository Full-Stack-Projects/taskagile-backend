package com.backend.taskagile.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class CustomExceptionHandler{

    @ExceptionHandler(value = {FieldValidationException.class})
    public ResponseEntity<Object> handleRequestException(FieldValidationException e){
        //Create payload containing exception details
        CustomDetailException customDetailException = new CustomDetailException(
                e.getMessage(),
                e,
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        //Return response entity
        return new ResponseEntity<>(customDetailException,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {FieldAvailableException.class})
    public ResponseEntity<Object> handleRequestException(FieldAvailableException e){
        //Create payload containing exception details
        CustomDetailException customDetailException = new CustomDetailException(
                e.getMessage(),
                e,
                HttpStatus.CONFLICT,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        //Return response entity
        return new ResponseEntity<>(customDetailException,HttpStatus.CONFLICT);
    }


}
