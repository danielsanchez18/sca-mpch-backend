package com.mpch.controlDeAsistencias_backend.servicesImpl;

import com.mpch.controlDeAsistencias_backend.model.Role;
import com.mpch.controlDeAsistencias_backend.model.Supervisor;
import com.mpch.controlDeAsistencias_backend.model.User;
import com.mpch.controlDeAsistencias_backend.repository.RoleRepository;
import com.mpch.controlDeAsistencias_backend.repository.SupervisorRepository;
import com.mpch.controlDeAsistencias_backend.services.SupervisorService;
import com.mpch.controlDeAsistencias_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SupervisorServiceImpl implements SupervisorService {

    @Autowired
    private SupervisorRepository supervisorRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Supervisor saveSupervisor(Supervisor supervisor) {

        // Validar que no exista ya un supervisor en el área
        if (supervisorRepository.existsByArea_IdArea(supervisor.getArea().getIdArea())) {
            throw new RuntimeException("El área ya tiene un supervisor asignado.");
        }

        Role supervisorRole = roleRepository.findById(2L).orElseGet(() -> {
            Role newRole = new Role();
            newRole.setIdRole(2L);
            newRole.setName("supervisor");
            return roleRepository.save(newRole);
        });

        supervisor.getUser().setRole(supervisorRole);

        if (supervisor.getUser().getIdUser() == null) {
            User savedUser = userService.save(supervisor.getUser());
            supervisor.setUser(savedUser);
        }

        return supervisorRepository.save(supervisor);
    }

    @Override
    public Supervisor getSupervisorById(String idSupervisor) {
        return supervisorRepository.findById(idSupervisor)
                .orElseThrow(() -> new RuntimeException("Supervisor no encontrado."));
    }

    @Override
    public Page<Supervisor> getAllSupervisors(Pageable pageable) {
        return supervisorRepository.findAll(pageable);
    }

    @Override
    public Page<Supervisor> searchSupervisorsByName(String name, Pageable pageable) {
        return supervisorRepository.findByFullName(name, pageable);
    }

    @Override
    public Page<Supervisor> getSupervisorsByDni(String area, Pageable pageable) {
        return supervisorRepository.findByUser_DniContainingIgnoreCase(area, pageable);
    }

    @Override
    public Page<Supervisor> getSupervisorsByArea(String area, Pageable pageable) {
        return supervisorRepository.findByArea_NameContainingIgnoreCase(area, pageable);
    }

    @Override
    public Long getTotalSupervisors() {
        return supervisorRepository.count();
    }

    @Override
    public Supervisor updateSupervisor(String idSupervisor, Supervisor supervisor) {

        Supervisor existingSupervisor = supervisorRepository.findById(idSupervisor).orElseThrow(
                () -> new RuntimeException("No se encontró el supervisor.")
        );

        boolean supervisorExistsInArea = supervisorRepository.existsByArea_IdArea(supervisor.getArea().getIdArea());
        if (supervisorExistsInArea && !existingSupervisor.getArea().getIdArea().equals(supervisor.getArea().getIdArea())) {
            throw new RuntimeException("El área ya tiene un supervisor asignado.");
        }

        User updatedUser = userService.updateUser(existingSupervisor.getUser().getIdUser(), supervisor.getUser());
        existingSupervisor.setUser(updatedUser);

        existingSupervisor.setArea(supervisor.getArea());
        existingSupervisor.setPassword(supervisor.getPassword());

        return supervisorRepository.save(existingSupervisor);
    }

    @Override
    public void deleteSupervisor(String idSupervisor) {
        supervisorRepository.deleteById(idSupervisor);
        userService.deleteUser(supervisorRepository.findById(idSupervisor).get().getUser().getIdUser());
    }
}
