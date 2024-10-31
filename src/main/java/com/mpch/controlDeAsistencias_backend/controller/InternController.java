package com.mpch.controlDeAsistencias_backend.controller;

import com.mpch.controlDeAsistencias_backend.model.Intern;
import com.mpch.controlDeAsistencias_backend.services.InternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/intern")
//@CrossOrigin("*")
@CrossOrigin(origins = "http://localhost:4200")
public class InternController {

    @Autowired
    private InternService internService;

    @PostMapping("/save")
    public Intern saveIntern(@RequestBody Intern intern) {
        return internService.saveIntern(intern);
    }

    @GetMapping("/all")
    public Page<Intern> getAllInterns(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        return internService.findAllInterns(pageable);
    }

    @GetMapping("/id/{idIntern}")
    public Intern getInternById(@PathVariable String idIntern) {
        return internService.findInternById(idIntern);
    }

    @GetMapping("/name/{name}")
    public List<Intern> searchInternsByName(@PathVariable String name, @PageableDefault(page = 1, size = 10) Pageable pageable) {
        return internService.searchInternsByName(name, pageable.getPageNumber(), pageable.getPageSize());
    }

    @GetMapping("/area/{area}")
    public List<Intern> findInternsByArea(@PathVariable String area, @PageableDefault(page = 1, size = 10) Pageable pageable) {
        return internService.findInternsByArea(area, pageable.getPageNumber(), pageable.getPageSize());
    }

    @GetMapping("/university/{university}")
    public List<Intern> findInternsByUniversity(@PathVariable String university, @PageableDefault(page = 1, size = 10) Pageable pageable) {
        return internService.findInternsByUniversity(university, pageable.getPageNumber(), pageable.getPageSize());
    }

    @GetMapping("/total")
    public Long getTotalInterns() {
        return internService.getTotalInterns();
    }

    @PutMapping("/update/{idIntern}")
    public Intern updateIntern(@PathVariable String idIntern, @RequestBody Intern intern) {
        return internService.updateIntern(idIntern, intern);
    }

    @DeleteMapping("/delete/{idIntern}")
    public void deleteIntern(@PathVariable UUID idIntern) {
        internService.deleteIntern(idIntern);
    }

}
