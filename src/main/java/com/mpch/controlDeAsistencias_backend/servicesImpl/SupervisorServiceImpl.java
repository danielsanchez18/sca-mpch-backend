package com.mpch.controlDeAsistencias_backend.servicesImpl;

import com.mpch.controlDeAsistencias_backend.model.Role;
import com.mpch.controlDeAsistencias_backend.model.Supervisor;
import com.mpch.controlDeAsistencias_backend.model.User;
import com.mpch.controlDeAsistencias_backend.repository.AreaRepository;
import com.mpch.controlDeAsistencias_backend.repository.RoleRepository;
import com.mpch.controlDeAsistencias_backend.repository.SupervisorRepository;
import com.mpch.controlDeAsistencias_backend.repository.UserRepository;
import com.mpch.controlDeAsistencias_backend.services.SupervisorService;
import com.mpch.controlDeAsistencias_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SupervisorServiceImpl implements SupervisorService {

    @Autowired
    private SupervisorRepository supervisorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AreaRepository areaRepository;

    @Override
    public Supervisor saveSupervisor(Supervisor supervisor) {

        Role supervisorRole = roleRepository.findByName("supervisor");
        User user = supervisor.getUser();
        user.setRole(supervisorRole);

        user = userService.save(user);

        String supervisorId = "S24" + user.getDni();
        supervisor.setIdSupervisor(supervisorId);

        if (!areaRepository.existsById(supervisor.getArea().getIdArea())) {
            throw new RuntimeException("Area no encontrada");
        }

        return supervisorRepository.save(supervisor);
    }

    @Override
    public Supervisor getSupervisorById(String idSupervisor) {
        return supervisorRepository.findById(idSupervisor).orElseThrow(
                () -> new RuntimeException("Supervisor no encontrado")

        );
    }

    @Override
    public Page<Supervisor> getAllSupervisors(Pageable pageable) {
        return supervisorRepository.findAll(pageable);
    }

    @Override
    public List<Supervisor> searchSupervisorsByName(String name, int page, int size) {
        return supervisorRepository.searchByName(name, page, size);
    }

    @Override
    public List<Supervisor> getSupervisorsByArea(String area, int page, int size) {
        return supervisorRepository.findByArea(area, page, size);
    }

    @Override
    public Long countSupervisors() {
        return supervisorRepository.count();
    }

    @Override
    public Supervisor updateSupervisor(String idSupervisor, Supervisor supervisor) {
        Supervisor existingSupervisor = getSupervisorById(idSupervisor);

        User updatedUser = supervisor.getUser();
        updatedUser.setRole(existingSupervisor.getUser().getRole());
        updatedUser = userService.updateUser(existingSupervisor.getUser().getIdUser(), updatedUser);

        existingSupervisor.setUser(updatedUser);

        if (!areaRepository.existsById(supervisor.getArea().getIdArea())) {
            throw new RuntimeException("Área no encontrada para el supervisor");
        }

        return supervisorRepository.save(existingSupervisor);
    }

    @Override
    public void deleteSupervisor(UUID idUser) {
        userRepository.deleteById(idUser);
    }
}
