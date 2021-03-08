package com.backend.taskagile.exception;

public class UserAlreadyExistsException extends Exception {

  public UserAlreadyExistsException(String message) {
    super(message);
  }
}
