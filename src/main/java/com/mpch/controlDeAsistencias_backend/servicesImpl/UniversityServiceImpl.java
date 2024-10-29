package com.mpch.controlDeAsistencias_backend.servicesImpl;

import com.mpch.controlDeAsistencias_backend.model.University;
import com.mpch.controlDeAsistencias_backend.repository.UniversityRepository;
import com.mpch.controlDeAsistencias_backend.services.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UniversityServiceImpl implements UniversityService {

    @Autowired
    private UniversityRepository universityRepository;

    private void validateUniversity(UUID idUniversity, String name, String acronym) {
        universityRepository.findByName(name).ifPresent(existingUniversity -> {
            if (!existingUniversity.getIdUniversity().equals(idUniversity)) {
                throw new RuntimeException("Ya existe una universidad con el nombre: " + name);
            }
        });

        universityRepository.findByAcronym(acronym).ifPresent(existingUniversity -> {
            if (!existingUniversity.getIdUniversity().equals(idUniversity)) {
                throw new RuntimeException("Ya existe una universidad con el acrónimo: " + acronym);
            }
        });
    }

    @Override
    public University createUniversity(University university) {
        validateUniversity(university.getIdUniversity(), university.getName(), university.getAcronym());
        return universityRepository.save(university);
    }

    @Override
    public University getUniversityById(UUID idUniversity) {
        return universityRepository.findById(idUniversity).orElseThrow(
                () -> new RuntimeException("No se encontró la universidad con ese ID")
        );
    }

    @Override
    public Page<University> getAllUniversities(Pageable pageable) {
        return universityRepository.findAll(pageable);
    }

    @Override
    public List<University> searchUniversityByName(String name, int page, int size) {
        return universityRepository.searchByName(name, page, size);
    }

    @Override
    public Long getTotalUniversities() {
        return universityRepository.count();
    }

    @Override
    public University updateUniversity(UUID idUniversity, University university) {
        University existingUniversity = getUniversityById(idUniversity);

        if (!existingUniversity.getName().equals(university.getName()) ||
                !existingUniversity.getAcronym().equals(university.getAcronym())) {
            validateUniversity(idUniversity, university.getName(), university.getAcronym());
        }

        existingUniversity.setName(university.getName());
        existingUniversity.setAcronym(university.getAcronym());
        existingUniversity.setPhoto(university.getPhoto());
        existingUniversity.setStatus(university.isStatus());

        return universityRepository.save(existingUniversity);
    }

    @Override
    public void deleteUniversity(UUID idUniversity) {
        universityRepository.deleteById(idUniversity);
    }
}
