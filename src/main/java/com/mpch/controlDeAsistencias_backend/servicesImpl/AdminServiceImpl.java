package com.mpch.controlDeAsistencias_backend.servicesImpl;

import com.mpch.controlDeAsistencias_backend.model.Admin;
import com.mpch.controlDeAsistencias_backend.repository.AdminRepository;
import com.mpch.controlDeAsistencias_backend.repository.UserRepository;
import com.mpch.controlDeAsistencias_backend.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Admin saveAdmin(Admin admin) {
        return null;
    }

    @Override
    public Admin getAdminById(String idAdmin) {
        return adminRepository.findById(idAdmin).orElseThrow(
                () -> new RuntimeException("No se encontró el administrador con ese ID")
        );
    }

    @Override
    public Page<Admin> getAllAdmins(Pageable pageable) {
        return adminRepository.findAll(pageable);
    }

    @Override
    public List<Admin> searchAdminsByName(String name, int page, int size) {
        return adminRepository.searchByName(name, page, size);
    }

    @Override
    public Long getTotalAdmins() {
        return adminRepository.count();
    }

    @Override
    public Admin updateAdmin(String idAdmin, Admin admin) {
        return null;
    }

    @Override
    public void deleteAdmin(UUID idUser) {
        userRepository.deleteById(idUser);
    }
}
