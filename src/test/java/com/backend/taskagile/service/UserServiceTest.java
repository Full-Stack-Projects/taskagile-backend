package com.backend.taskagile.service;

import com.backend.taskagile.dto.UserDto;
import com.backend.taskagile.exception.UserAlreadyExistsException;
import com.backend.taskagile.model.User;
import com.backend.taskagile.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private static final String userNameForTest = "userTest";
    private static final String userEmailForTest = "useremail@test.com";

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("test successful registration")
    void testRegisterSuccess() throws UserAlreadyExistsException {
        UserDto userDto = userDtoStub();

        when(userRepository.findByUsername(userNameForTest)).thenReturn(Optional.empty());
        when(userRepository.findByEmail(userEmailForTest)).thenReturn(Optional.empty());

        userService.register(userDto);

        verify(userRepository, times(1)).findByUsername(userNameForTest);
        verify(userRepository, times(1)).findByEmail(userEmailForTest);
        verify(userRepository, times(1)).save(any(User.class));

    }

    @Test
    @DisplayName("test register user when username already exists")
    void testRegisterWhenUserNameExists() {
        UserDto userDto = userDtoStub();

        when(userRepository.findByUsername(userNameForTest)).thenReturn(Optional.ofNullable(new User()));

        UserAlreadyExistsException exception = assertThrows(UserAlreadyExistsException.class,
                () -> userService.register(userDto));

        assertThat(exception.getMessage()).isEqualTo("Username not available");
        verify(userRepository, times(1)).findByUsername(userNameForTest);
        verify(userRepository, never()).save(any(User.class));
    }

    private static UserDto userDtoStub() {
        return UserDto.builder().firstName("userFirstName")
                .lastName("userLastName").username("userTest")
                .email("useremail@test.com").build();
    }



}
