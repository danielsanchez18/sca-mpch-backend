package com.mpch.controlDeAsistencias_backend.controller;

import com.mpch.controlDeAsistencias_backend.model.University;
import com.mpch.controlDeAsistencias_backend.services.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/university")
//@CrossOrigin("*")
@CrossOrigin(origins = "http://localhost:4200")

public class UniversityController {

    @Autowired
    private UniversityService universityService;

    @PostMapping("/save")
    public University saveUniversity(@RequestBody University university) {
        return universityService.createUniversity(university);
    }

    @GetMapping("/id/{idUniversity}")
    public University getUniversityById(@PathVariable UUID idUniversity) {
        return universityService.getUniversityById(idUniversity);
    }

    @GetMapping("/all")
    public Page<University> getAllUniversities(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        return universityService.getAllUniversities(pageable);
    }

    @GetMapping("/search/{name}")
    public List<University> searchUniversityByName(@PathVariable String name, @PageableDefault(page = 1, size = 10) Pageable pageable) {
        return universityService.searchUniversityByName(name, pageable.getPageNumber(), pageable.getPageSize());
    }

    @GetMapping("/total")
    public Long getTotalUniversities() {
        return universityService.getTotalUniversities();
    }

    @PutMapping("/update/{idUniversity}")
    public University updateUniversity(@PathVariable UUID idUniversity, @RequestBody University university) {
        return universityService.updateUniversity(idUniversity, university);
    }

    @DeleteMapping("/delete/{idUniversity}")
    public void deleteUniversity(@PathVariable UUID idUniversity) {
        universityService.deleteUniversity(idUniversity);
    }

}
