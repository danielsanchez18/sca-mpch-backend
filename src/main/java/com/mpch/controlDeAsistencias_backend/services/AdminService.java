package com.mpch.controlDeAsistencias_backend.services;

import com.mpch.controlDeAsistencias_backend.model.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface AdminService {

    Admin saveAdmin(Admin admin);

    Admin getAdminById(String idAdmin);

    Page<Admin> getAllAdmins(Pageable pageable);

    List<Admin> searchAdminsByName(String name, int page, int size);

    Long getTotalAdmins();

    Admin updateAdmin(String idAdmin, Admin admin);

    void deleteAdmin(UUID idUser);
}
