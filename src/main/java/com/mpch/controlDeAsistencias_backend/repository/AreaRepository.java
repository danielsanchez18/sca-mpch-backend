package com.mpch.controlDeAsistencias_backend.repository;

import com.mpch.controlDeAsistencias_backend.model.Area;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AreaRepository extends JpaRepository<Area, Long> {

    boolean existsByName(String name);

    Optional<Area> findByName (String name);

    Page<Area> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
