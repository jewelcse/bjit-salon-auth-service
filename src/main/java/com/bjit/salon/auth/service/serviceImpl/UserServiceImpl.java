package com.bjit.salon.auth.service.serviceImpl;


import com.bjit.salon.auth.service.dto.request.UserRegisterDto;
import com.bjit.salon.auth.service.dto.response.RegisterResponseDto;
import com.bjit.salon.auth.service.entity.ERole;
import com.bjit.salon.auth.service.entity.Role;
import com.bjit.salon.auth.service.entity.User;
import com.bjit.salon.auth.service.exception.RoleNotFoundException;
import com.bjit.salon.auth.service.exception.UserEmailAlreadyTakenException;
import com.bjit.salon.auth.service.exception.UserNotFoundException;
import com.bjit.salon.auth.service.repository.RoleRepository;
import com.bjit.salon.auth.service.repository.UserRepository;
import com.bjit.salon.auth.service.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;


    @Override
    public RegisterResponseDto createUserAccount(UserRegisterDto registerDto) {

        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new UserEmailAlreadyTakenException("email \"" + registerDto.getEmail() + "\" already taken!");
        }
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            throw new UserEmailAlreadyTakenException("username \"" + registerDto.getUsername() + "\" already taken!");
        }

        Set<Role> roles = new HashSet<>();
        if (registerDto.getRole() != null) {
            switch (registerDto.getRole()) {
                case "ROLE_STAFF":
                    Role staffRole = roleRepository.findByName(ERole.ROLE_STAFF)
                            .orElseThrow(() -> new RoleNotFoundException(ERole.ROLE_STAFF + " doesn't exist!"));
                    roles.add(staffRole);
                    break;

                case "ROLE_ADMIN":
                    Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RoleNotFoundException(ERole.ROLE_ADMIN + " doesn't exist!"));
                    roles.add(adminRole);
                    break;

                case "ROLE_SUPER_ADMIN":
                    Role superAdminRole = roleRepository.findByName(ERole.ROLE_SUPER_ADMIN)
                            .orElseThrow(() -> new RoleNotFoundException(ERole.ROLE_SUPER_ADMIN + " doesn't exist!"));
                    roles.add(superAdminRole);
                    break;
                default:
                    Role defaultRole = roleRepository.findByName(ERole.ROLE_USER)
                            .orElseThrow(() -> new RoleNotFoundException(ERole.ROLE_USER + " doesn't exist!"));
                    roles.add(defaultRole);
            }
        }

        User user = User.builder()
                .firstName(registerDto.getFirstName())
                .lastName(registerDto.getLastName())
                .username(registerDto.getUsername())
                .email(registerDto.getEmail())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .roles(roles)
                .enabled(true)
                .nonLocked(true)
                .build();

        User response = userRepository.save(user);

        return RegisterResponseDto.builder()
                .firstName(response.getFirstName())
                .lastName(response.getLastName())
                .username(response.getUsername())
                .email(response.getEmail())
                .enabled(response.isEnabled())
                .nonLocked(response.isNonLocked())
                .roles(response.getRoles())
                .build();
    }

    @Override
    public boolean activateDeactivateUserAccount(long id) {
        Optional<User> user = userRepository.findById(id); // mock
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found for id: " + id);
        }
        if (user.get().isNonLocked()) {
            user.get().setNonLocked(false);
            userRepository.save(user.get()); //mock
            return false;
        }
        user.get().setNonLocked(true);
        userRepository.save(user.get()); // mock
        return true;
    }
}
