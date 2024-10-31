package com.mpch.controlDeAsistencias_backend.controller;

import com.mpch.controlDeAsistencias_backend.model.Supervisor;
import com.mpch.controlDeAsistencias_backend.services.SupervisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/supervisor")
//@CrossOrigin("*")
@CrossOrigin(origins = "http://localhost:4200")
public class SupervisorController {

    @Autowired
    private SupervisorService supervisorService;

    @PostMapping("/save")
    public Supervisor saveSupervisor(@RequestBody Supervisor supervisor){
        return supervisorService.saveSupervisor(supervisor);
    }

    @GetMapping("/id/{idSupervisor}")
    public Supervisor getSupervisorById(@PathVariable String idSupervisor){
        return supervisorService.getSupervisorById(idSupervisor);
    }

    @GetMapping("/all")
    public Page<Supervisor> getAllSupervisors(@PageableDefault(page = 0, size = 10) Pageable pageable){
        return supervisorService.getAllSupervisors(pageable);
    }

    @GetMapping("/name/{name}")
    public List<Supervisor> searchSupervisorsByName(@PathVariable String name, @PageableDefault(page = 1, size = 10) Pageable pageable){
        return supervisorService.searchSupervisorsByName(name, pageable.getPageNumber(), pageable.getPageSize());
    }

    @GetMapping("/area/{area}")
    public List<Supervisor> getSupervisorsByArea(@PathVariable String area, @PageableDefault(page = 1, size = 10) Pageable pageable){
        return supervisorService.getSupervisorsByArea(area, pageable.getPageNumber(), pageable.getPageSize());
    }

    @GetMapping("/total")
    public Long countSupervisors(){
        return supervisorService.countSupervisors();
    }

    @PutMapping("/update/{idSupervisor}")
    public Supervisor updateSupervisor(@PathVariable String idSupervisor, @RequestBody Supervisor supervisor){
        return supervisorService.updateSupervisor(idSupervisor, supervisor);
    }

    @DeleteMapping("/delete/{idUser}")
    public void deleteSupervisor(@PathVariable UUID idUser){
        supervisorService.deleteSupervisor(idUser);
    }

}
