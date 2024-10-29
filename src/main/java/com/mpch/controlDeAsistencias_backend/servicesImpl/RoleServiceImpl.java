package com.mpch.controlDeAsistencias_backend.servicesImpl;

import com.mpch.controlDeAsistencias_backend.model.Role;
import com.mpch.controlDeAsistencias_backend.repository.RoleRepository;
import com.mpch.controlDeAsistencias_backend.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    private void validateRole(String name) {
        Role role = roleRepository.findByName(name);
        if (role != null) {
            throw new RuntimeException("Rol ya existe");
        }

    }

    @Override
    public Role saveRole(Role role) {
        validateRole(role.getName());
        return roleRepository.save(role);
    }

    @Override
    public Role findRoleById(UUID idRole) {
        return roleRepository.findById(idRole).orElseThrow(
                () -> new RuntimeException("Rol no encontrado")
        );
    }

    @Override
    public Page<Role> getAllRoles(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }

    @Override
    public List<Role> searchRoleByName(String name, int page, int size) {
        return roleRepository.searchByName(name, page, size);
    }

    @Override
    public Long getTotalRoles() {
        return roleRepository.count();
    }

    @Override
    public Role updateRole(UUID idRole, Role role) {
        Role existingRole = findRoleById(idRole);

        if (!existingRole.getName().equals(role.getName())) {
            validateRole(role.getName());
        }

        existingRole.setName(role.getName());

        return roleRepository.save(existingRole);
    }

    @Override
    public void deleteRole(UUID idRole) {
        roleRepository.deleteById(idRole);
    }
}
