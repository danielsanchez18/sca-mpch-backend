package com.mpch.controlDeAsistencias_backend.repository;

import com.mpch.controlDeAsistencias_backend.model.Certificated;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CertificatedRepository extends JpaRepository<Certificated, UUID>{
}
