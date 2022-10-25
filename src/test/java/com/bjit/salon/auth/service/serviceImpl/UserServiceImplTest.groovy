package com.bjit.salon.auth.service.serviceImpl

import com.bjit.salon.auth.service.dto.request.UserRegisterDto
import com.bjit.salon.auth.service.entity.ERole
import com.bjit.salon.auth.service.entity.Role
import com.bjit.salon.auth.service.entity.User
import com.bjit.salon.auth.service.exception.RoleNotFoundException
import com.bjit.salon.auth.service.exception.UserEmailAlreadyTakenException
import com.bjit.salon.auth.service.exception.UserNotFoundException
import com.bjit.salon.auth.service.repository.RoleRepository
import com.bjit.salon.auth.service.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.password.PasswordEncoder
import spock.lang.Specification

@SpringBootTest
class UserServiceImplTest extends Specification {

    private UserRepository userRepository
    private RoleRepository roleRepository
    @Autowired
    private PasswordEncoder passwordEncoder
    private UserServiceImpl userService

    def setup() {
        userRepository = Mock()
        roleRepository = Mock()
        userService = new UserServiceImpl(userRepository, passwordEncoder, roleRepository)
    }

    def "should create a new account with admin role"() {
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
                .password(passwordEncoder.encode("jewel"))
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

    def "should throw admin role not found exception whiling creating a new admin account"(){
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

        userRepository.existsByEmail("jewel@gmail.com") >> false
        userRepository.existsByUsername("jewel") >> false
        roleRepository.findByName(ERole.ROLE_ADMIN) >> Optional.ofNullable(null)

        when:
        userService.createUserAccount(userCreateRequest)

        then:
        def exception = thrown(RoleNotFoundException)
        exception.message == "ROLE_ADMIN doesn't exist!"


    }

    def "should throw UserEmailAlreadyTakenException for duplicate email"() {
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
                .password(passwordEncoder.encode("jewel"))
                .enabled(true)
                .nonLocked(true)
                .roles(hasRole)
                .build()


        userRepository.existsByEmail("jewel@gmail.com") >> true
        userRepository.existsByUsername("jewel") >> false
        roleRepository.findByName(ERole.ROLE_ADMIN) >> Optional.of(adminRole)
        userRepository.save(_) >> user

        when:
        userService.createUserAccount(userCreateRequest)

        then:
        def exception = thrown(UserEmailAlreadyTakenException)
        exception.message == "email \"jewel@gmail.com\" already taken!"
    }

    def "should throw UserEmailAlreadyTakenException for duplicate username"() {
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
                .password(passwordEncoder.encode("jewel"))
                .enabled(true)
                .nonLocked(true)
                .roles(hasRole)
                .build()


        userRepository.existsByEmail("jewel@gmail.com") >> false
        userRepository.existsByUsername("jewel") >> true
        roleRepository.findByName(ERole.ROLE_ADMIN) >> Optional.of(adminRole)
        userRepository.save(_) >> user

        when:
        userService.createUserAccount(userCreateRequest)

        then:
        def exception = thrown(UserEmailAlreadyTakenException)
        exception.message == "username \"jewel\" already taken!"
    }

    def "should create a new account with staff role"() {
        given:
        def userCreateRequest = UserRegisterDto
                .builder()
                .firstName("jewel")
                .lastName("chowdhury")
                .email("jewel@gmail.com")
                .username("jewel")
                .password("jewel")
                .role("ROLE_STAFF")
                .build()

        def staffRole = Role.builder()
                .id(1)
                .name(ERole.ROLE_STAFF)
                .build()
        def hasRole = new HashSet<Role>()
        hasRole.add(staffRole)

        def user = User.builder()
                .firstName("jewel")
                .lastName("chowdhury")
                .email("jewel@gmail.com")
                .username("jewel")
                .password(passwordEncoder.encode("jewel"))
                .enabled(true)
                .nonLocked(true)
                .roles(hasRole)
                .build()


        userRepository.existsByEmail("jewel@gmail.com") >> false
        userRepository.existsByUsername("jewel") >> false
        roleRepository.findByName(ERole.ROLE_STAFF) >> Optional.of(staffRole)
        userRepository.save(_) >> user

        when:
        def response = userService.createUserAccount(userCreateRequest)

        then:
        response.getEmail() == "jewel@gmail.com"
    }

    def "should throw staff role not found exception whiling creating a new staff account"(){
        given:
        def userCreateRequest = UserRegisterDto
                .builder()
                .firstName("jewel")
                .lastName("chowdhury")
                .email("jewel@gmail.com")
                .username("jewel")
                .password("jewel")
                .role("ROLE_STAFF")
                .build()

        userRepository.existsByEmail("jewel@gmail.com") >> false
        userRepository.existsByUsername("jewel") >> false
        roleRepository.findByName(ERole.ROLE_STAFF) >> Optional.ofNullable(null)

        when:
        userService.createUserAccount(userCreateRequest)

        then:
        def exception = thrown(RoleNotFoundException)
        exception.message == "ROLE_STAFF doesn't exist!"


    }

    def "should create a new account with user role"() {
        given:
        def userCreateRequest = UserRegisterDto
                .builder()
                .firstName("jewel")
                .lastName("chowdhury")
                .email("jewel@gmail.com")
                .username("jewel")
                .password("jewel")
                .role("ROLE_USER")
                .build()

        def userRole = Role.builder()
                .id(1)
                .name(ERole.ROLE_USER)
                .build()
        def hasRole = new HashSet<Role>()
        hasRole.add(userRole)

        def user = User.builder()
                .firstName("jewel")
                .lastName("chowdhury")
                .email("jewel@gmail.com")
                .username("jewel")
                .password(passwordEncoder.encode("jewel"))
                .enabled(true)
                .nonLocked(true)
                .roles(hasRole)
                .build()


        userRepository.existsByEmail("jewel@gmail.com") >> false
        userRepository.existsByUsername("jewel") >> false
        roleRepository.findByName(ERole.ROLE_USER) >> Optional.of(userRole)
        userRepository.save(_) >> user

        when:
        def response = userService.createUserAccount(userCreateRequest)

        then:
        response.getEmail() == "jewel@gmail.com"
    }

    def "should throw user role not found exception whiling creating a new user account"(){
        given:
        def userCreateRequest = UserRegisterDto
                .builder()
                .firstName("jewel")
                .lastName("chowdhury")
                .email("jewel@gmail.com")
                .username("jewel")
                .password("jewel")
                .role("ROLE_USER")
                .build()

        userRepository.existsByEmail("jewel@gmail.com") >> false
        userRepository.existsByUsername("jewel") >> false
        roleRepository.findByName(ERole.ROLE_USER) >> Optional.ofNullable(null)

        when:
        userService.createUserAccount(userCreateRequest)

        then:
        def exception = thrown(RoleNotFoundException)
        exception.message == "ROLE_USER doesn't exist!"


    }

    def "should create a new account with super admin role"() {
        given:
        def userCreateRequest = UserRegisterDto
                .builder()
                .firstName("jewel")
                .lastName("chowdhury")
                .email("jewel@gmail.com")
                .username("jewel")
                .password("jewel")
                .role("ROLE_SUPER_ADMIN")
                .build()

        def superAdminRole = Role.builder()
                .id(1)
                .name(ERole.ROLE_SUPER_ADMIN)
                .build()
        def hasRole = new HashSet<Role>()
        hasRole.add(superAdminRole)

        def user = User.builder()
                .firstName("jewel")
                .lastName("chowdhury")
                .email("jewel@gmail.com")
                .username("jewel")
                .password(passwordEncoder.encode("jewel"))
                .enabled(true)
                .nonLocked(true)
                .roles(hasRole)
                .build()


        userRepository.existsByEmail("jewel@gmail.com") >> false
        userRepository.existsByUsername("jewel") >> false
        roleRepository.findByName(ERole.ROLE_SUPER_ADMIN) >> Optional.of(superAdminRole)
        userRepository.save(_) >> user

        when:
        def response = userService.createUserAccount(userCreateRequest)

        then:
        response.getEmail() == "jewel@gmail.com"
    }

    def "should throw super admin role not found exception whiling creating a new super admin account"(){
        given:
        def userCreateRequest = UserRegisterDto
                .builder()
                .firstName("jewel")
                .lastName("chowdhury")
                .email("jewel@gmail.com")
                .username("jewel")
                .password("jewel")
                .role("ROLE_SUPER_ADMIN")
                .build()

        userRepository.existsByEmail("jewel@gmail.com") >> false
        userRepository.existsByUsername("jewel") >> false
        roleRepository.findByName(ERole.ROLE_SUPER_ADMIN) >> Optional.ofNullable(null)

        when:
        userService.createUserAccount(userCreateRequest)

        then:
        def exception = thrown(RoleNotFoundException)
        exception.message == "ROLE_SUPER_ADMIN doesn't exist!"


    }

    def "should activate the user account"() {

        given:
        def user = User.builder()
                .firstName("jewel")
                .lastName("chowdhury")
                .email("jewel@gmail.com")
                .username("jewel")
                .enabled(true)
                .nonLocked(false)
                .build()

        userRepository.findById(1L) >> Optional.of(user)
        userRepository.save(_) >> user

        when:
        def response = userService.activateDeactivateUserAccount(1L)

        then:
        response
    }

    def "should deactivate the user account"() {

        given:
        def user = User.builder()
                .firstName("jewel")
                .lastName("chowdhury")
                .email("jewel@gmail.com")
                .username("jewel")
                .enabled(true)
                .nonLocked(true)
                .build()

        userRepository.findById(1L) >> Optional.of(user)
        userRepository.save(_) >> user

        when:
        def response = userService.activateDeactivateUserAccount(1L)

        then:
        !response
    }

    def "should throw user not found exception while activating or deactivating the account"() {

        given:
        def user = User.builder()
                .id(2L)
                .firstName("jewel")
                .lastName("chowdhury")
                .email("jewel@gmail.com")
                .username("jewel")
                .enabled(true)
                .nonLocked(true)
                .build()

        userRepository.findById(1L) >> Optional.ofNullable(null)
        //userRepository.save(_) >> user

        when:
        userService.activateDeactivateUserAccount(1L)

        then:
        def exception = thrown(UserNotFoundException)
        exception.message == "User not found for id: 1"
    }


}