package com.bjit.salon.auth.service.dto.user.service.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import static javax.swing.text.StyleConstants.Size;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRegisterDto {

    @NotEmpty(message = "first name must be required")
    private String firstName;
    @NotEmpty(message = "last name must be required")
    private String lastName;
    @NotEmpty(message = "username must be required")
    private String username;
    @NotEmpty(message = "email must be required")
    @Email(message = "valid email required")
    private String email;
    @NotEmpty(message = "password must be required")
    private String password;
    @NotEmpty(message = "role must be required")
    private String role;
}
