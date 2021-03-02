package com.backend.taskagile.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(value = HttpStatus.CONFLICT,reason = "Invalid")
public class FieldValidationException extends Exception {

    public FieldValidationException(String message){
        super(message);
    }

}
