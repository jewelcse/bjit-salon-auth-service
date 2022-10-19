package com.bjit.salon.auth.service

import com.bjit.salon.auth.service.dto.user.service.request.UserRegisterDto
import com.bjit.salon.auth.service.dto.user.service.response.RegisterResponseDto
import com.bjit.salon.auth.service.entity.ERole
import com.bjit.salon.auth.service.entity.Role
import com.bjit.salon.auth.service.entity.User
import com.bjit.salon.auth.service.repository.RoleRepository
import com.bjit.salon.auth.service.repository.UserRepository
import com.bjit.salon.auth.service.serviceImpl.UserServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.password.PasswordEncoder
import spock.lang.Specification

@SpringBootTest
class UserServiceImplTests extends Specification {

    private UserRepository userRepository
    private RoleRepository roleRepository
    private PasswordEncoder passwordEncoder
    private UserServiceImpl userService

    def setup() {
        userRepository = Mock()
        roleRepository = Mock()
        userService = new UserServiceImpl(userRepository, passwordEncoder, roleRepository)
    }

    def "create a new account"() {

        given:

        def userCreateRequest = UserRegisterDto
                .builder()
                .firstName("jewel")
                .lastName("chowdhury")
                .email("jewel@gmail.com")
                .username("jewel")
                .password("jewel")
                .role("ROLE_ADMIN")
                .build()

        def adminRole = Role.builder()
                .id(1)
                .name(ERole.ROLE_ADMIN)
                .build()
        def hasRole = new HashSet<Role>()
        hasRole.add(adminRole)

        def user = User.builder()
                .firstName("jewel")
                .lastName("chowdhury")
                .email("jewel@gmail.com")
                .username("jewel")
                .enabled(true)
                .nonLocked(true)
                .roles(hasRole)
                .build()


        userRepository.existsByEmail("jewel@gmail.com") >> false
        userRepository.existsByUsername("jewel") >> false
        roleRepository.findByName(ERole.ROLE_ADMIN) >> Optional.of(adminRole)
        userRepository.save(_) >> user

        when:
        def response = userService.createUserAccount(userCreateRequest)

        then:
        response.getEmail() == "jewel@gmail.com"
    }


}