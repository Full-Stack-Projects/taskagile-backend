package com.backend.taskagile.controller;

import com.backend.taskagile.model.User;
import com.backend.taskagile.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/v1")
public class UserController {

    private UserService userService;

    @PostMapping("/registration")
    public User registerUser(@RequestBody User user){
        User newUser = userService.register(user);
        return newUser;
    }
}
