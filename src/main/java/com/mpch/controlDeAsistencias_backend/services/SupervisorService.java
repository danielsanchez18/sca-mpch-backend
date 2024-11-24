package com.mpch.controlDeAsistencias_backend.services;

import com.mpch.controlDeAsistencias_backend.model.Supervisor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SupervisorService {

    Supervisor saveSupervisor(Supervisor supervisor);

    Supervisor getSupervisorById(String idSupervisor);

    Page<Supervisor> getAllSupervisors(Pageable pageable);

    Page<Supervisor> searchSupervisorsByName(String name, Pageable pageable);

    Page<Supervisor> getSupervisorsByDni(String area, Pageable pageable);

    Page<Supervisor> getSupervisorsByArea(String area, Pageable pageable);

    Long getTotalSupervisors();

    Supervisor updateSupervisor(String idSupervisor, Supervisor supervisor);

    void deleteSupervisor(String idSupervisor);
}
