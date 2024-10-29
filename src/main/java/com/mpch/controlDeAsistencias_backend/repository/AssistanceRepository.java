package com.mpch.controlDeAsistencias_backend.repository;

import com.mpch.controlDeAsistencias_backend.model.Assistance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AssistanceRepository extends JpaRepository<Assistance, String>{
}
