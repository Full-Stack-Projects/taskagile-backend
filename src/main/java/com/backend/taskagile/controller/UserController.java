package com.backend.taskagile.controller;

import com.backend.taskagile.exception.FieldValidationException;
import com.backend.taskagile.model.User;
import com.backend.taskagile.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/v1")
@Validated
public class UserController {

    private UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<User> registerUser(@Valid @RequestBody User user) throws Exception {
            User newUser = userService.
                    register(user);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);

    }
}
