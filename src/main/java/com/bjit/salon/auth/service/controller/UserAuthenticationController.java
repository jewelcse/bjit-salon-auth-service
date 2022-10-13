package com.bjit.salon.auth.service.controller;


import com.bjit.salon.auth.service.dto.user.service.request.UserLoginDto;
import com.bjit.salon.auth.service.dto.user.service.request.UserRegisterDto;
import com.bjit.salon.auth.service.dto.user.service.response.LoginResponseDto;
import com.bjit.salon.auth.service.security.jwt.JwtUtil;
import com.bjit.salon.auth.service.service.UserService;
import com.bjit.salon.auth.service.serviceImpl.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.bjit.salon.auth.service.util.ConstraintsUtil.APPLICATION_BASE_URL;
@AllArgsConstructor
@RestController
@RequestMapping(APPLICATION_BASE_URL)
public class UserAuthenticationController {

    private static final Logger log = LoggerFactory.getLogger(UserAuthenticationController.class);

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/sign-in")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserLoginDto loginDto) {
        log.info("Signing with username: {}",loginDto.getUsername());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return ResponseEntity.ok( LoginResponseDto.builder()
                        .token(jwt)
                        .type("Bearer")
                        .username(userDetails.getUsername())
                        .email(userDetails.getEmail())
                        .roles(roles)
                .build());
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> registerAccount(@Valid @RequestBody UserRegisterDto registerDto) {
        log.info("Creating new account with username: {}",registerDto.getUsername());
        userService.createUserAccount(registerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Register new account success");
    }

}
