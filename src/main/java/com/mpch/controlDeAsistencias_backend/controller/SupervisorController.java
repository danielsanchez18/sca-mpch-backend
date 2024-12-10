package com.mpch.controlDeAsistencias_backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mpch.controlDeAsistencias_backend.model.Supervisor;
import com.mpch.controlDeAsistencias_backend.services.SupervisorService;
import com.mpch.controlDeAsistencias_backend.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/supervisor")
@CrossOrigin(origins = "http://localhost:4200")
public class SupervisorController {

    @Autowired
    private SupervisorService supervisorService;

    private static final Logger logger = LoggerFactory.getLogger(SupervisorController.class);

    @PostMapping("/add")
    public ResponseEntity<?> addSupervisor(@RequestBody Supervisor supervisor) {
        try {
            logger.info("Iniciando la creación del supervisor: {}", supervisor.getUser().getName());
            Supervisor createdSupervisor = supervisorService.saveSupervisor(supervisor);
            logger.info("Supervisor creado exitosamente con ID: {}", createdSupervisor.getIdSupervisor());
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ResponseUtils.successResponse("Supervisor creado exitosamente", createdSupervisor));
        } catch (RuntimeException ex) {
            logger.error("Error al crear el supervisor: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseUtils.errorResponse(ex.getMessage()));
        }
    }

    @GetMapping("/id/{idSupervisor}")
    public ResponseEntity<?> getSupervisorById(@PathVariable String idSupervisor) {
        try {
            logger.info("Buscando supervisor con ID: {}", idSupervisor);
            Supervisor supervisor = supervisorService.getSupervisorById(idSupervisor);
            logger.info("Supervisor encontrado: {}", supervisor.getUser().getName());
            return ResponseEntity.ok(ResponseUtils.successResponse("Supervisor encontrado", supervisor));
        } catch (RuntimeException ex) {
            logger.error("Supervisor no encontrado con ID: {} - {}", idSupervisor, ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtils.errorResponse(ex.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllSupervisors(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            logger.info("Obteniendo lista de supervisores, página: {}, tamaño: {}", pageable.getPageNumber(), pageable.getPageSize());
            Page<Supervisor> supervisors = supervisorService.getAllSupervisors(pageable);
            logger.info("Supervisores obtenidos: {}", supervisors.getTotalElements());
            return ResponseEntity.ok(ResponseUtils.successResponse("Lista de supervisores obtenida exitosamente", supervisors));
        } catch (Exception ex) {
            logger.error("Error al obtener la lista de supervisores: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtils.errorResponse("Error al obtener la lista de supervisores"));
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> searchSupervisorsByName(@PathVariable String name, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            logger.info("Buscando supervisores por nombre: {}", name);
            Page<Supervisor> supervisors = supervisorService.searchSupervisorsByName(name, pageable);
            logger.info("Supervisores encontrados con nombre: {}", supervisors.getTotalElements());
            return ResponseEntity.ok(ResponseUtils.successResponse("Supervisores encontrados", supervisors));
        } catch (Exception ex) {
            logger.error("Error al buscar supervisores por nombre: {} - {}", name, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtils.errorResponse("Error al buscar los supervisores"));
        }
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<?> searchSupervisorsByDni(@PathVariable String dni, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            logger.info("Buscando supervisores por DNI: {}", dni);
            Page<Supervisor> supervisors = supervisorService.getSupervisorsByDni(dni, pageable);
            logger.info("Supervisores encontrados con DNI: {}", supervisors.getTotalElements());
            return ResponseEntity.ok(ResponseUtils.successResponse("Supervisores encontrados", supervisors));
        } catch (Exception ex) {
            logger.error("Error al buscar supervisores por DNI: {} - {}", dni, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtils.errorResponse("Error al buscar los supervisores"));
        }
    }

    @GetMapping("/area/{area}")
    public ResponseEntity<?> searchSupervisorsByArea(@PathVariable String area, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            logger.info("Buscando supervisores por área: {}", area);
            Page<Supervisor> supervisors = supervisorService.getSupervisorsByArea(area, pageable);
            logger.info("Supervisores encontrados en área: {}", supervisors.getTotalElements());
            return ResponseEntity.ok(ResponseUtils.successResponse("Supervisores encontrados", supervisors));
        } catch (Exception ex) {
            logger.error("Error al buscar supervisores por área: {} - {}", area, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtils.errorResponse("Error al buscar los supervisores"));
        }
    }

    @GetMapping("/total")
    public Long getTotalSupervisors() {
        long totalSupervisors = supervisorService.getTotalSupervisors();
        logger.info("Total de supervisores: {}", totalSupervisors);
        return totalSupervisors;
    }

    @PutMapping("/update/{idSupervisor}")
    public ResponseEntity<?> updateSupervisor(@PathVariable String idSupervisor, @RequestBody Supervisor supervisor) {
        try {
            logger.info("Actualizando supervisor con ID: {}", idSupervisor);
            Supervisor updatedSupervisor = supervisorService.updateSupervisor(idSupervisor, supervisor);
            logger.info("Supervisor actualizado con ID: {}", updatedSupervisor.getIdSupervisor());
            return ResponseEntity.ok(ResponseUtils.successResponse("Supervisor actualizado exitosamente", updatedSupervisor));
        } catch (RuntimeException ex) {
            logger.error("Error al actualizar el supervisor con ID: {} - {}", idSupervisor, ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtils.errorResponse(ex.getMessage()));
        }
    }

    @DeleteMapping("/delete/{idSupervisor}")
    public ResponseEntity<?> deleteSupervisor(@PathVariable String idSupervisor) {
        try {
            logger.info("Eliminando supervisor con ID: {}", idSupervisor);
            supervisorService.deleteSupervisor(idSupervisor);
            logger.info("Supervisor eliminado con ID: {}", idSupervisor);
            return ResponseEntity.ok(ResponseUtils.successResponse("Supervisor eliminado exitosamente", null));
        } catch (RuntimeException ex) {
            logger.error("Error al eliminar el supervisor con ID: {} - {}", idSupervisor, ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtils.errorResponse(ex.getMessage()));
        }
    }
}