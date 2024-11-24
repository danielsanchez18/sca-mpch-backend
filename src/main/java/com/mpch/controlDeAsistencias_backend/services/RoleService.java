package com.mpch.controlDeAsistencias_backend.services;

import com.mpch.controlDeAsistencias_backend.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoleService {

    Role saveRole(Role role);

    Role findRoleById(Long idRole);

    Page<Role> getAllRoles(Pageable pageable);

    Long getTotalRoles();

    Role updateRole(Long idRole, Role role);

    void deleteRole(Long idRole);

}
