package com.mpch.controlDeAsistencias_backend.repository;

import com.mpch.controlDeAsistencias_backend.model.AreaUniversity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AreaUniversityRepository extends JpaRepository<AreaUniversity, UUID> {

    boolean existsByArea_IdAreaAndUniversity_IdUniversity(Long areaId, Long universityId);

    Page<AreaUniversity> findByArea_NameContainingIgnoreCaseOrUniversity_NameContainingIgnoreCase(
            String areaName, String universityName, Pageable pageable);


    Page<AreaUniversity> findByUniversity_IdUniversity(Long idUniversity, Pageable pageable);

    Page<AreaUniversity> findByArea_IdArea(Long idArea, Pageable pageable);
}
