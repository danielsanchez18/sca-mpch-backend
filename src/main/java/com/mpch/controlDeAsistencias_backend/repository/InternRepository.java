package com.mpch.controlDeAsistencias_backend.repository;

import com.mpch.controlDeAsistencias_backend.model.Admin;
import com.mpch.controlDeAsistencias_backend.model.Intern;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface InternRepository extends JpaRepository<Intern, String> {

    boolean existsByAreaUniversity_Area_IdArea(Long idArea);

    boolean existsByAreaUniversity_University_IdUniversity(Long idUniversity);

    boolean existsByAreaUniversity_IdAreaUniversity(UUID idAreaUniversity);

    @Query(value = "SELECT i.* FROM intern i " +
            "JOIN user u ON i.id_user = u.id_user " +
            "WHERE LOWER(CONCAT(u.name, ' ', u.lastname)) LIKE LOWER(CONCAT('%', :fullName, '%'))",
            nativeQuery = true)
    Page<Intern> findByFullName(@Param("fullName") String fullName, Pageable pageable);

    Page<Intern> findByAreaUniversity_Area_NameContainingIgnoreCase(String name, Pageable pageable);

    Page<Intern> findByAreaUniversity_University_NameContainingIgnoreCase(String name, Pageable pageable);

    Page<Intern> findByAreaUniversity_IdAreaUniversity(UUID idAreaUniversity, Pageable pageable);

    long countByAreaUniversity_IdAreaUniversity(UUID idAreaUniversity);

    Optional<Intern> findByUser_Dni(String dni);

    @Query("SELECT i FROM Intern i " +
            "WHERE i.totalHours >= i.areaUniversity.hoursCertified")
    Page<Intern> findEligibleInterns(Pageable pageable);

}
