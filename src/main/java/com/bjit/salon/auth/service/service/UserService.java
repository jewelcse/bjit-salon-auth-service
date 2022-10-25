package com.bjit.salon.auth.service.service;

import com.bjit.salon.auth.service.dto.request.UserRegisterDto;
import com.bjit.salon.auth.service.dto.response.RegisterResponseDto;

public interface UserService {
    RegisterResponseDto createUserAccount(UserRegisterDto registerDto);
    boolean activateDeactivateUserAccount(long id);

}
