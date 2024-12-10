package com.mpch.controlDeAsistencias_backend.repository;

import com.mpch.controlDeAsistencias_backend.model.Security;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SecurityRepository extends JpaRepository<Security, String> {
    // Page<Security> findByUser_NameContainingIgnoreCaseOrUser_LastnameContainingIgnoreCase(String name, Pageable pageable);

    @Query(value = "SELECT s.* FROM security s " +
            "JOIN user u ON s.id_user = u.id_user " +
            "WHERE LOWER(CONCAT(u.name, ' ', u.lastname)) LIKE LOWER(CONCAT('%', :fullName, '%'))",
            nativeQuery = true)
    Page<Security> findByFullName(@Param("fullName") String fullName, Pageable pageable);

    Page<Security> findByUser_DniContainingIgnoreCase(String dni, Pageable pageable);

    Optional<Security> findByUser_Dni(String dni);

}

