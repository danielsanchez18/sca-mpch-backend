package com.mpch.controlDeAsistencias_backend.repository;

import com.mpch.controlDeAsistencias_backend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    boolean existsByName(String name);

}
