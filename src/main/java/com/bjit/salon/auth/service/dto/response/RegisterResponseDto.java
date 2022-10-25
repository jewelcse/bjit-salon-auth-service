package com.bjit.salon.auth.service.dto.response;

import com.bjit.salon.auth.service.entity.Role;
import lombok.*;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class RegisterResponseDto {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private boolean enabled;
    private boolean nonLocked;
    private Set<Role> roles = new HashSet<>();
}
