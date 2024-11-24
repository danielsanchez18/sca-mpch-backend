package com.mpch.controlDeAsistencias_backend.servicesImpl;

import com.mpch.controlDeAsistencias_backend.model.Admin;
import com.mpch.controlDeAsistencias_backend.model.Role;
import com.mpch.controlDeAsistencias_backend.model.User;
import com.mpch.controlDeAsistencias_backend.repository.AdminRepository;
import com.mpch.controlDeAsistencias_backend.repository.RoleRepository;
import com.mpch.controlDeAsistencias_backend.services.AdminService;
import com.mpch.controlDeAsistencias_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    // Validar que exista por lo menos un administrador
    private void validateSingleAdmin() {
        if (adminRepository.count() == 1) {
            throw new RuntimeException("El sistema no puede quedar sin administradores.");
        }
    }

    @Override
    public Admin saveAdmin(Admin admin) {

        Role adminRole = roleRepository.findById(1L).orElseGet(() -> {
            Role newRole = new Role();
            newRole.setIdRole(1L);
            newRole.setName("administrador");
            return roleRepository.save(newRole);
        });

        admin.getUser().setRole(adminRole);

        if (admin.getUser().getIdUser() == null) {
            User savedUser = userService.save(admin.getUser());
            admin.setUser(savedUser);
        }

        return adminRepository.save(admin);
    }

    @Override
    public Admin getAdminById(String idAdmin) {
        return adminRepository.findById(idAdmin).orElseThrow(
                () -> new RuntimeException("No se encontró el administrador."));
    }

    @Override
    public Page<Admin> getAllAdmins(Pageable pageable) {
        return adminRepository.findAll(pageable);
    }

    @Override
    public Page<Admin> searchAdminsByName(String name, Pageable pageable) {
        return adminRepository.
                findByFullName(name, pageable);
    }

    @Override
    public Page<Admin> searchAdminsByDni(String dni, Pageable pageable) {
        return adminRepository.
                findByUser_DniContainingIgnoreCase(dni, pageable);
    }

    @Override
    public Long getTotalAdmins() {
        return adminRepository.count();
    }

    @Override
    public Admin updateAdmin(String idAdmin, Admin admin) {
        Admin existingAdmin = adminRepository.findById(idAdmin).orElseThrow(
                () -> new RuntimeException("No se encontró el administrador.")
        );

        User updatedUser = userService.updateUser(existingAdmin.getUser().getIdUser(), admin.getUser());
        existingAdmin.setUser(updatedUser);

        existingAdmin.setPassword(admin.getPassword());

        return adminRepository.save(existingAdmin);
    }

    @Override
    public void deleteAdmin(String idAdmin) {
        validateSingleAdmin();
        adminRepository.deleteById(idAdmin);
        userService.deleteUser(adminRepository.findById(idAdmin).get().getUser().getIdUser());
    }
}
