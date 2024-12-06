package com.mpch.controlDeAsistencias_backend.repository;

import com.mpch.controlDeAsistencias_backend.model.Certificated;
import com.mpch.controlDeAsistencias_backend.model.Intern;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface CertificatedRepository extends JpaRepository<Certificated, UUID> {

    Optional<Certificated> findByIntern_User_Dni(String dni);

    @Query("SELECT c FROM Certificated c " +
            "WHERE LOWER(CONCAT(c.intern.user.name, ' ', c.intern.user.lastname)) " +
            "LIKE LOWER(CONCAT('%', :fullName, '%'))")
    Page<Certificated> findByInternFullName(@Param("fullName") String fullName, Pageable pageable);

    Page<Certificated> findByStatus(boolean status, Pageable pageable);

}
