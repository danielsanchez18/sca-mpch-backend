package com.mpch.controlDeAsistencias_backend.repository;

import com.mpch.controlDeAsistencias_backend.model.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, String> {

    // Page<Admin> findByUser_NameContainingIgnoreCaseOrUser_LastnameContainingIgnoreCase(String name, Pageable pageable);

    @Query(value = "SELECT a.* FROM admin a " +
            "JOIN user u ON a.id_user = u.id_user " +
            "WHERE LOWER(CONCAT(u.name, ' ', u.lastname)) LIKE LOWER(CONCAT('%', :fullName, '%'))",
            nativeQuery = true)
    Page<Admin> findByFullName(@Param("fullName") String fullName, Pageable pageable);

    Page<Admin> findByUser_DniContainingIgnoreCase(String dni, Pageable pageable);

    Optional<Admin> findByUser_Dni(String dni);

}
