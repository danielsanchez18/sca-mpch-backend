package com.mpch.controlDeAsistencias_backend.services;

import com.mpch.controlDeAsistencias_backend.model.University;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UniversityService {

    University createUniversity(University university);

    University getUniversityById(Long idUniversity);

    Page<University> getAllUniversities(Pageable pageable);

    Page<University> searchUniversityByName(String name, Pageable pageable);

    Long getTotalUniversities();

    University updateUniversity(Long idUniversity, University university);

    void deleteUniversity(Long idUniversity);
}
