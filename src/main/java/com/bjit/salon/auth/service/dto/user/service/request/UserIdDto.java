package com.bjit.salon.auth.service.dto.user.service.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserIdDto {

    @NotBlank(message = "user id can't be empty")
    private long id;
}
