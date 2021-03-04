package com.backend.taskagile.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column
  @NotBlank
  private String firstName;
  @Column
  @NotBlank
  private String lastName;
  @Column
  @NotBlank
  private String email;
  @Column
  @NotBlank
  private String username;
  @Column
  @NotBlank
  private String password;

  public User(String firstName,
      String lastName,
      String email,
      String username,
      String password) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.username = username;
    this.password = password;
  }
}
