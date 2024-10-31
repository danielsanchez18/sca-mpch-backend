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

    List<Intern> searchInternsByName(String name, int page, int size);

    List<Intern> findInternsByArea(String area, int page, int size);

    List<Intern> findInternsByUniversity(String university, int page, int size);

    Long getTotalInterns();

    Intern updateIntern(String idIntern, Intern intern);

    void deleteIntern(UUID idUser);

}
