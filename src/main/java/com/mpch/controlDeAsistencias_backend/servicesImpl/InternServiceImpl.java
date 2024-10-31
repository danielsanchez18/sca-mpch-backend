package com.mpch.controlDeAsistencias_backend.servicesImpl;

import com.mpch.controlDeAsistencias_backend.model.Area;
import com.mpch.controlDeAsistencias_backend.model.Intern;
import com.mpch.controlDeAsistencias_backend.model.Role;
import com.mpch.controlDeAsistencias_backend.model.User;
import com.mpch.controlDeAsistencias_backend.repository.*;
import com.mpch.controlDeAsistencias_backend.services.InternService;
import com.mpch.controlDeAsistencias_backend.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InternServiceImpl implements InternService {

    @Autowired
    private InternRepository internRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private UniversityRepository universityRepository;

    private static final String INTERN_PREFIX = "I24";
    private static final String INTERN_ROLE = "practicante";

    private static final Logger logger = LoggerFactory.getLogger(InternServiceImpl.class);

    // Metodo para generar el ID del practicante
    private String generateInternId(String dni) {
        return INTERN_PREFIX + dni;
    }

    // Metodo para guardar el rol "practicante"
    private User createUserForIntern(Intern intern) {
        Role internRole = roleRepository.findByName(INTERN_ROLE);
        intern.getUser().setRole(internRole);

        return userService.save(intern.getUser());
    }

    // Metodo para validar que el area y la universidad existan
    private void validateAreaAndUniversityExistence(Intern intern) {
        areaRepository.findById(intern.getArea().getIdArea())
                .orElseThrow(() -> new RuntimeException("El área especificada no existe"));

        universityRepository.findById(intern.getUniversity().getIdUniversity())
                .orElseThrow(() -> new RuntimeException("La universidad especificada no existe"));
    }

    // Metodo para validar que haya vacantes disponibles en un area
    private void validateVacancies(UUID idArea) {
        Area area = areaRepository.findById(idArea)
                .orElseThrow(() -> new RuntimeException("El área especificada no existe"));

        long registeredInterns = internRepository.countByArea_IdArea(idArea);

        if (registeredInterns >= area.getNro_vacancies()) {
            throw new RuntimeException("No hay vacantes disponibles en el área especificada");

        }
    }

    private boolean hasAreaChanged(Intern existingIntern, Intern updatedIntern) {
        return !existingIntern.getArea().getIdArea().equals(updatedIntern.getArea().getIdArea());
    }

    @Override
    public Intern saveIntern(Intern intern) {
        validateAreaAndUniversityExistence(intern);
        validateVacancies(intern.getArea().getIdArea());

        User user = createUserForIntern(intern);

        intern.setIdIntern(generateInternId(user.getDni()));
        intern.setUser(user);

        return internRepository.save(intern);
    }

    @Override
    public Intern findInternById(String idIntern) {
        return internRepository.findById(idIntern).orElseThrow(
                () -> new RuntimeException("No se encontró el practicante con ese ID")
        );
    }

    @Override
    public Page<Intern> findAllInterns(Pageable pageable) {
        return internRepository.findAll(pageable);
    }

    @Override
    public List<Intern> searchInternsByName(String name, int page, int size) {
        return internRepository.searchByName(name, page, size);
    }

    @Override
    public List<Intern> findInternsByArea(String area, int page, int size) {
        return internRepository.findByArea(area, page, size);
    }

    @Override
    public List<Intern> findInternsByUniversity(String university, int page, int size) {
        return internRepository.findByUniversity(university, page, size);
    }

    @Override
    public Long getTotalInterns() {
        return internRepository.count();
    }

    @Override
    public Intern updateIntern(String idIntern, Intern intern) {
        Intern existingIntern = findInternById(idIntern);

        System.out.println(existingIntern.getUser().getIdUser() + ": IdUser");

        if (hasAreaChanged(existingIntern, intern)) {
            validateVacancies(intern.getArea().getIdArea());
        }

        System.out.println(existingIntern.getUser().getIdUser() + ": IdUser2");

        validateAreaAndUniversityExistence(intern);

        existingIntern.setArea(intern.getArea());
        existingIntern.setUniversity(intern.getUniversity());
        existingIntern.setTotalHours(intern.getTotalHours());

        User updatedUser = intern.getUser();
        updatedUser.setRole(existingIntern.getUser().getRole());
        updatedUser = userService.updateUser(existingIntern.getUser().getIdUser(), updatedUser);
        existingIntern.setUser(updatedUser);

        System.out.println(existingIntern.getUser().getIdUser() + ": IdUser3");

        return internRepository.save(existingIntern);
    }

    @Override
    public void deleteIntern(UUID idUser) {
        userRepository.deleteById(idUser);
    }

}
