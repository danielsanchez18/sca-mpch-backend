package com.mpch.controlDeAsistencias_backend.servicesImpl;

import com.mpch.controlDeAsistencias_backend.model.Role;
import com.mpch.controlDeAsistencias_backend.repository.RoleRepository;
import com.mpch.controlDeAsistencias_backend.repository.UserRepository;
import com.mpch.controlDeAsistencias_backend.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    // Validar nombre único
    private void validateUniqueRoleName(String name) {
        if (roleRepository.existsByName(name)) {
            throw new RuntimeException("El rol con el nombre '" + name + "' ya existe.");
        }
    }

    // Validar rol existente
    private void validateExistingRole(Long idRole) {
        if (!roleRepository.existsById(idRole)) {
            throw new IllegalArgumentException("Rol no encontrado con el ID " + idRole);
        }
    }

    // Validar que el rol no esté en uso
    private void validateRoleInUse(Long idRole) {
        boolean isInUse = userRepository.existsByRole_IdRole(idRole);
        if (isInUse) {
            throw new RuntimeException("El rol no se puede eliminar porque está en uso por usuarios.");
        }
    }

    @Override
    public Role saveRole(Role role) {
        validateUniqueRoleName(role.getName());
        return roleRepository.save(role);
    }

    @Override
    public Role findRoleById(Long idRole) {
        return roleRepository.findById(idRole).orElseThrow(
                () -> new RuntimeException("Rol no encontrado")
        );
    }

    @Override
    public Page<Role> getAllRoles(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }

    @Override
    public Long getTotalRoles() {
        return roleRepository.count();
    }

    @Override
    public Role updateRole(Long idRole, Role role) {
        validateExistingRole(idRole);
        validateUniqueRoleName(role.getName());

        Role existingRole = roleRepository.findById(idRole).orElseThrow();
        existingRole.setName(role.getName());

        return roleRepository.save(existingRole);
    }

    @Override
    public void deleteRole(Long idRole) {
        validateExistingRole(idRole);
        validateRoleInUse(idRole);

        roleRepository.deleteById(idRole);
    }

}
