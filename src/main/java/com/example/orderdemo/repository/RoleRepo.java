package com.example.orderdemo.repository;

import com.example.orderdemo.entity.ERole;
import com.example.orderdemo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(ERole roleName);
}
