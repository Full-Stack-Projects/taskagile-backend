package com.backend.taskagile.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.backend.taskagile.dto.UserDto;
import com.backend.taskagile.exception.UserAlreadyExistsException;
import com.backend.taskagile.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;


@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private UserService serviceMock;

  @Test
  void registerUserFailedBlank() throws Exception {
    
    mvc.perform(post("/users/v1/registration"))
        .andExpect(status().is(400));
  }

  @Test
  public void registerUserSucceed() throws Exception {

    UserDto userTest = UserDto.builder()
        .firstName("Pablo")
        .lastName("Valdes")
        .email("pablov@mail.com")
        .username("pabloVal")
        .password("123456").build();

    doNothing().when(serviceMock)
        .register(userTest);

    ObjectMapper mapper = new ObjectMapper();
    String requestJson = mapper.writeValueAsString(userTest);

    mvc.perform(post("/users/v1/registration")
        .contentType(MediaType.APPLICATION_JSON)
        .content(requestJson))
        .andExpect(status().isCreated());
  }

  @Test
  @DisplayName("test register user when username already exists")
  public void testRegisterExistingUser() throws Exception {
    UserDto userTest = UserDto.builder()
        .firstName("Pablo")
        .lastName("Valdes")
        .email("pablov@mail.com")
        .username("pabloVal")
        .password("123456").build();

    doThrow(UserAlreadyExistsException.class)
        .when(serviceMock)
        .register(any(UserDto.class));

    ObjectMapper mapper = new ObjectMapper();
    String requestJson = mapper.writeValueAsString(userTest);

    mvc.perform(post("/users/v1/registration")
        .contentType(MediaType.APPLICATION_JSON)
        .content(requestJson))
        .andExpect(status().is(409));
  }

}