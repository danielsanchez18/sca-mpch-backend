package com.mpch.controlDeAsistencias_backend.services;

import com.mpch.controlDeAsistencias_backend.model.University;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface UniversityService {

    University createUniversity(University university);

    University getUniversityById(UUID idUniversity);

    Page<University> getAllUniversities(Pageable pageable);

    List<University> searchUniversityByName(String name, int page, int size);

    Long getTotalUniversities();

    University updateUniversity(UUID idUniversity, University university);

    void deleteUniversity(UUID idUniversity);
}
