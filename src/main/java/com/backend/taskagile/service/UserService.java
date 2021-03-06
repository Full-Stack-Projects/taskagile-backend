package com.backend.taskagile.service;

import com.backend.taskagile.dto.UserDto;
import com.backend.taskagile.exception.UserAlreadyExistsException;
import com.backend.taskagile.model.User;
import com.backend.taskagile.repository.UserRepository;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public void register(UserDto user) throws UserAlreadyExistsException {
    if (checkUsernameAvailable(user) && checkEmailAvailable(user)) {
      User newUser = User.builder()
          .firstName(user.getFirstName())
          .lastName(user.getLastName())
          .email(user.getEmail())
          .username(user.getUsername())
          .password(user.getPassword())
          .build();
      userRepository.save(newUser);
    }
  }

  private boolean checkUsernameAvailable(UserDto user) throws UserAlreadyExistsException {
    Optional<User> userFound = userRepository.findByUsername(user.getUsername());
    if (userFound.isPresent()) {
      throw new UserAlreadyExistsException("Username not available");
    }
    return true;
  }

  private boolean checkEmailAvailable(UserDto user) throws UserAlreadyExistsException {
    Optional<User> userFound = userRepository.findByEmail(user.getEmail());
    if (userFound.isPresent()) {
      throw new UserAlreadyExistsException("Email not available");
    }
    return true;
  }
}
