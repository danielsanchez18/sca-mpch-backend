package com.mpch.controlDeAsistencias_backend.repository;

import com.mpch.controlDeAsistencias_backend.model.Supervisor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SupervisorRepository extends JpaRepository<Supervisor, String> {

    boolean existsByArea_IdArea(Long idArea);

    @Query(value = "SELECT s.* FROM supervisor s " +
            "JOIN user u ON s.id_user = u.id_user " +
            "WHERE LOWER(CONCAT(u.name, ' ', u.lastname)) LIKE LOWER(CONCAT('%', :fullName, '%'))",
            nativeQuery = true)
    Page<Supervisor> findByFullName(@Param("fullName") String fullName, Pageable pageable);

    Page<Supervisor> findByUser_DniContainingIgnoreCase(String dni, Pageable pageable);

    Page<Supervisor> findByArea_NameContainingIgnoreCase(String area, Pageable pageable);

    Optional<Supervisor> findByUser_Dni(String dni);

}
