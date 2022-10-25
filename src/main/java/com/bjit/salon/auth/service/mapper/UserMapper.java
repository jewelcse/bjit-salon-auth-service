package com.bjit.salon.auth.service.mapper;


import com.bjit.salon.auth.service.dto.request.UserRegisterDto;
import com.bjit.salon.auth.service.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {


    User toUser(UserRegisterDto registerDto);


}
