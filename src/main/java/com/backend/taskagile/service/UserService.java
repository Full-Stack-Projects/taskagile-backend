package com.backend.taskagile.service;

import com.backend.taskagile.model.User;
import com.backend.taskagile.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User register(User user){
        return userRepository.save(user);
    }
}
