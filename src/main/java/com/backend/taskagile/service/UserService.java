package com.backend.taskagile.service;

import com.backend.taskagile.exception.FieldAvailableException;
import com.backend.taskagile.exception.FieldValidationException;
import com.backend.taskagile.model.User;
import com.backend.taskagile.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User register(User user) throws Exception {
        if(checkUsernameAvailable(user) && checkEmailAvailable(user)){
            return userRepository.save(user);
        }
        return user;
    }

    private boolean checkUsernameAvailable(User user) throws Exception{
        Optional<User> userFound = userRepository.findByUsername(user.getUsername());
        if(userFound.isPresent()){
            throw new FieldAvailableException("Username not available");
        }
        return true;
    }

    private boolean checkEmailAvailable(User user) throws Exception{
        Optional<User> userFound = userRepository.findByEmail(user.getEmail());
        if(userFound.isPresent()){
            throw new FieldAvailableException("Email not available");
        }
        return true;
    }
}
