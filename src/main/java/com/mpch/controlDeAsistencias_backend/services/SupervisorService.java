package com.mpch.controlDeAsistencias_backend.services;

import com.mpch.controlDeAsistencias_backend.model.Supervisor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface SupervisorService {

    Supervisor saveSupervisor(Supervisor supervisor);

    Supervisor getSupervisorById(String idSupervisor);

    Page<Supervisor> getAllSupervisors(Pageable pageable);

    List<Supervisor> searchSupervisorsByName(String name, int page, int size);

    List<Supervisor> getSupervisorsByArea(String area, int page, int size);

    Long countSupervisors();

    Supervisor updateSupervisor(String idSupervisor, Supervisor supervisor);

    void deleteSupervisor(UUID idUser);
}
