package com.bjit.salon.auth.service.controller;

import com.bjit.salon.auth.service.dto.user.service.request.UserIdDto;
import com.bjit.salon.auth.service.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.bjit.salon.auth.service.util.ConstraintsUtil.APPLICATION_BASE_URL;

@AllArgsConstructor
@RestController
@RequestMapping(APPLICATION_BASE_URL)
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @PostMapping("/activateDeactivate")
    public ResponseEntity<String> activateDeactivateUser(@Valid @RequestBody UserIdDto userIdDto) {
        boolean isUserAccountActive=userService.activateDeactivateUserAccount(userIdDto.getId());
        if (isUserAccountActive){
            log.info("Activating user account with user id: {}",userIdDto.getId());
            return ResponseEntity.status(HttpStatus.OK).body("User account activated");
        }
        log.info("Deactivating user account with user id: {}",userIdDto.getId());
        return ResponseEntity.status(HttpStatus.OK).body("User Account deactivated");
    }


}
