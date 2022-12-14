package com.bjit.salon.auth.service.dto.request;


import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import static javax.swing.text.StyleConstants.Size;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
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
