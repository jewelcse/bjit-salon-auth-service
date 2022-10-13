package com.bjit.salon.auth.service.dto.user.service.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserLoginDto {
    @NotEmpty(message = "username must be required")
    private String username;
    @NotEmpty(message = "password must be required")
    private String password;
}
