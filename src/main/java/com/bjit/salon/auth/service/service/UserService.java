package com.bjit.salon.auth.service.service;

import com.bjit.salon.auth.service.dto.user.service.request.UserRegisterDto;
import com.bjit.salon.auth.service.dto.user.service.response.RegisterResponseDto;

public interface UserService {
    RegisterResponseDto createUserAccount(UserRegisterDto registerDto);
    boolean activateDeactivateUserAccount(long id);

}
