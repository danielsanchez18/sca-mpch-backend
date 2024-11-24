package com.mpch.controlDeAsistencias_backend.services;

import com.mpch.controlDeAsistencias_backend.model.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminService {

    Admin saveAdmin(Admin admin);

    Admin getAdminById(String idAdmin);

    Page<Admin> getAllAdmins(Pageable pageable);

    Page<Admin> searchAdminsByName(String name, Pageable pageable);

    Page<Admin> searchAdminsByDni(String dni, Pageable pageable);

    Long getTotalAdmins();

    Admin updateAdmin(String idAdmin, Admin admin);

    void deleteAdmin(String idAmind);

}
