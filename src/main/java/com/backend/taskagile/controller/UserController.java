package com.backend.taskagile.controller;

import com.backend.taskagile.dto.UserDto;
import com.backend.taskagile.service.UserService;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/users/v1")
public class UserController {

  private UserService userService;

  @PostMapping("/registration")
  public ResponseEntity<Void> registerUser(@Valid @RequestBody UserDto user) throws Exception {
    userService.register(user);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }
}
