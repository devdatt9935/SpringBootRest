package com.dev.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    @NotEmpty(message = "firstName should not be empty")
    private String firstName;
    @NotEmpty(message = "lastName should not be empty")
    private String lastName;
    @NotEmpty(message = "email should not be empty")
    @Email
    private String email;
}
