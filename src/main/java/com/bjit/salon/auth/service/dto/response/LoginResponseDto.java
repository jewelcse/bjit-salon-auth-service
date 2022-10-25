package com.bjit.salon.auth.service.dto.response;

import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class LoginResponseDto {

    private String token;
    private String type = "Bearer";
    private String username;
    private String email;
    private List<String> roles;
}
