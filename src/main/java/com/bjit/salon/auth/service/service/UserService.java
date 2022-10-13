package com.bjit.salon.auth.service.service;

import com.bjit.salon.auth.service.dto.user.service.request.UserRegisterDto;

public interface UserService {
    void createUserAccount(UserRegisterDto registerDto);
    boolean activateDeactivateUserAccount(long id);

}
