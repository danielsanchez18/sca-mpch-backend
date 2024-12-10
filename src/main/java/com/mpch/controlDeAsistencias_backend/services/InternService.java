package com.mpch.controlDeAsistencias_backend.services;

import com.mpch.controlDeAsistencias_backend.model.Intern;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface InternService {

    Intern saveIntern(Intern intern);

    Intern findInternById(String idIntern);

    Page<Intern> findAllInterns(Pageable pageable);

    Page<Intern> searchInternsByName(String name, Pageable pageable);

    Page<Intern> findInternsByArea(String area, Pageable pageable);

    Page<Intern> findInternsByUniversity(String university, Pageable pageable);

    Page<Intern> findInternsByAreaUniversity(UUID idAreaUniversity, Pageable pageable);

    Long getTotalInterns();

    Intern updateIntern(String idIntern, Intern intern);

    void deleteIntern(String idIntern);

    // void deleteIntern(UUID idIntern);

}
