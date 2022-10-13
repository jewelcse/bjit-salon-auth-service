package com.bjit.salon.auth.service.repository;

import com.bjit.salon.auth.service.entity.ERole;
import com.bjit.salon.auth.service.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(ERole roleUser);
}
