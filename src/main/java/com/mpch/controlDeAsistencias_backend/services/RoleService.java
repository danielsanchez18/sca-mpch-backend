package com.mpch.controlDeAsistencias_backend.services;

import com.mpch.controlDeAsistencias_backend.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface RoleService {

    Role saveRole(Role role);

    Role findRoleById(UUID idRole);

    Page<Role> getAllRoles(Pageable pageable);

    List<Role> searchRoleByName(String name, int page, int size);

    Long getTotalRoles();

    Role updateRole(UUID idRole, Role role);

    void deleteRole(UUID idRole);

}
