package com.mpch.controlDeAsistencias_backend.servicesImpl;

import com.mpch.controlDeAsistencias_backend.model.AreaUniversity;
import com.mpch.controlDeAsistencias_backend.model.Intern;
import com.mpch.controlDeAsistencias_backend.model.Role;
import com.mpch.controlDeAsistencias_backend.model.User;
import com.mpch.controlDeAsistencias_backend.repository.AreaUniversityRepository;
import com.mpch.controlDeAsistencias_backend.repository.AssistanceRepository;
import com.mpch.controlDeAsistencias_backend.repository.InternRepository;
import com.mpch.controlDeAsistencias_backend.repository.RoleRepository;
import com.mpch.controlDeAsistencias_backend.services.InternService;
import com.mpch.controlDeAsistencias_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InternServiceImpl implements InternService {

    @Autowired
    private InternRepository internRepository;

    @Autowired
    private AssistanceRepository assistanceRepository;

    @Autowired
    private AreaUniversityRepository areaUniversityRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    // Validar que existan vacantes en el área requerida
    private void validateVacancies(UUID idAreaUniversity) {
        AreaUniversity areaUniversity = areaUniversityRepository.findById(idAreaUniversity).orElseThrow(
                () -> new RuntimeException("No se encontró el área-universidad.")
        );
        long currentInterns = internRepository.countByAreaUniversity_IdAreaUniversity(idAreaUniversity);
        if (currentInterns >= areaUniversity.getArea().getNroVacancies()) {
            throw new RuntimeException("El área ya alcanzó el número máximo de vacantes.");
        }
    }

    @Override
    public Intern saveIntern(Intern intern) {

        validateVacancies(intern.getAreaUniversity().getIdAreaUniversity());

        Role internRole = roleRepository.findById(4L).orElseGet(() -> {
            Role newRole = new Role();
            newRole.setIdRole(4L);
            newRole.setName("practicante");
            return roleRepository.save(newRole);
        });

        intern.getUser().setRole(internRole);
        // intern.setTotalHours(0L);
        // System.out.println("Total Hours: " + intern.getTotalHours());

        if (intern.getUser().getIdUser() == null) {
            User savedUser = userService.save(intern.getUser());
            intern.setUser(savedUser);
        }

        return internRepository.save(intern);
    }

    @Override
    public Intern findInternById(String idIntern) {
        return internRepository.findById(idIntern).orElseThrow(
                () -> new RuntimeException("No se encontró el practicante.")
        );
    }

    @Override
    public Page<Intern> findAllInterns(Pageable pageable) {
        return internRepository.findAll(pageable);
    }

    @Override
    public Page<Intern> searchInternsByName(String name, Pageable pageable) {
        return internRepository.findByFullName(name, pageable);
    }

    @Override
    public Page<Intern> findInternsByArea(String area, Pageable pageable) {
        return internRepository.findByAreaUniversity_Area_NameContainingIgnoreCase(area, pageable);
    }

    @Override
    public Page<Intern> findInternsByUniversity(String university, Pageable pageable) {
        return internRepository.findByAreaUniversity_University_NameContainingIgnoreCase(university, pageable);
    }

    @Override
    public Page<Intern> findInternsByAreaUniversity(UUID idAreaUniversity, Pageable pageable) {
        return internRepository.findByAreaUniversity_IdAreaUniversity(idAreaUniversity, pageable);
    }

    @Override
    public Long getTotalInterns() {
        return internRepository.count();
    }

    @Override
    public Intern updateIntern(String idIntern, Intern intern) {

        Intern existingIntern = internRepository.findById(idIntern).orElseThrow(
                () -> new RuntimeException("No se encontró el practicante.")
        );

        if (!existingIntern.getAreaUniversity().getIdAreaUniversity().equals(intern.getAreaUniversity().getIdAreaUniversity())) {
            validateVacancies(intern.getAreaUniversity().getIdAreaUniversity());
        }

        User updatedUser = userService.updateUser(existingIntern.getUser().getIdUser(), intern.getUser());
        existingIntern.setUser(updatedUser);

        existingIntern.setAreaUniversity(intern.getAreaUniversity());
        existingIntern.setTotalHours(intern.getTotalHours());

        return internRepository.save(existingIntern);
    }

    @Override
    public void deleteIntern(String idIntern) {

        Intern intern = internRepository.findById(idIntern).orElseThrow(
                () -> new RuntimeException("No se encontró el practicante.")
        );

        assistanceRepository.deleteById(intern.getUser().getIdUser());

        internRepository.deleteById(idIntern);
        userService.deleteUser(intern.getUser().getIdUser());
    }
}
