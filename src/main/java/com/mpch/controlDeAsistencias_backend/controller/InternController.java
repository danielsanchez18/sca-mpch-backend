package com.mpch.controlDeAsistencias_backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mpch.controlDeAsistencias_backend.model.Intern;
import com.mpch.controlDeAsistencias_backend.services.InternService;
import com.mpch.controlDeAsistencias_backend.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/intern")
@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin("*")
public class InternController {

    @Autowired
    private InternService internService;

    private static final Logger logger = LoggerFactory.getLogger(InternController.class);

    @PostMapping("/add")
    public ResponseEntity<?> addIntern(@RequestBody Intern intern) {
        logger.info("Solicitud para agregar un practicante: {}", intern);
        try {
            Intern createdIntern = internService.saveIntern(intern);
            logger.info("Practicante creado exitosamente: {}", createdIntern);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ResponseUtils.successResponse("Practicante creado exitosamente", createdIntern));
        } catch (RuntimeException ex) {
            logger.error("Error al crear el practicante: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseUtils.errorResponse(ex.getMessage()));
        } catch (Exception ex) {
            logger.error("Error inesperado al crear el practicante: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtils.errorResponse("Error al crear el practicante"));
        }
    }

    @GetMapping("/id/{idIntern}")
    public ResponseEntity<?> getInternById(@PathVariable String idIntern) {
        logger.info("Solicitud para obtener practicante con ID: {}", idIntern);
        try {
            Intern intern = internService.findInternById(idIntern);
            logger.info("Practicante encontrado con ID: {}", idIntern);
            return ResponseEntity.ok(ResponseUtils.successResponse("Practicante encontrado", intern));
        } catch (RuntimeException ex) {
            logger.error("Practicante no encontrado con ID: {}. Error: {}", idIntern, ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtils.errorResponse(ex.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllInterns(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        logger.info("Solicitud para obtener lista de practicantes con paginación: Página {}, Tamaño {}", pageable.getPageNumber(), pageable.getPageSize());
        try {
            Page<Intern> interns = internService.findAllInterns(pageable);
            logger.info("Lista de practicantes obtenida exitosamente. Total de practicantes: {}", interns.getTotalElements());
            return ResponseEntity.ok(ResponseUtils.successResponse("Lista de practicantes obtenida exitosamente", interns));
        } catch (Exception ex) {
            logger.error("Error al obtener la lista de practicantes: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtils.errorResponse("Error al obtener la lista de practicantes"));
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> searchInternsByName(@PathVariable String name, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        logger.info("Solicitud para buscar practicantes por nombre: {}", name);
        try {
            Page<Intern> interns = internService.searchInternsByName(name, pageable);
            logger.info("Practicantes encontrados con nombre: {}", name);
            return ResponseEntity.ok(ResponseUtils.successResponse("Practicantes encontrados", interns));
        } catch (Exception ex) {
            logger.error("Error al buscar practicantes por nombre: {}. Error: {}", name, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtils.errorResponse("Error al buscar practicantes"));
        }
    }

    @GetMapping("/area/{area}")
    public ResponseEntity<?> findInternsByArea(@PathVariable String area, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        logger.info("Solicitud para buscar practicantes por área: {}", area);
        try {
            Page<Intern> interns = internService.findInternsByArea(area, pageable);
            logger.info("Practicantes encontrados para área: {}", area);
            return ResponseEntity.ok(ResponseUtils.successResponse("Practicantes encontrados", interns));
        } catch (Exception ex) {
            logger.error("Error al buscar practicantes por área: {}. Error: {}", area, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtils.errorResponse("Error al buscar practicantes"));
        }
    }

    @GetMapping("/university/{university}")
    public ResponseEntity<?> findInternsByUniversity(@PathVariable String university, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        logger.info("Solicitud para buscar practicantes por universidad: {}", university);
        try {
            Page<Intern> interns = internService.findInternsByUniversity(university, pageable);
            logger.info("Practicantes encontrados para universidad: {}", university);
            return ResponseEntity.ok(ResponseUtils.successResponse("Practicantes encontrados", interns));
        } catch (Exception ex) {
            logger.error("Error al buscar practicantes por universidad: {}. Error: {}", university, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtils.errorResponse("Error al buscar practicantes"));
        }
    }

    @GetMapping("/area-university/{idAreaUniversity}")
    public ResponseEntity<?> findInternsByAreaUniversity(@PathVariable UUID idAreaUniversity, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        logger.info("Solicitud para buscar practicantes por área universitaria con ID: {}", idAreaUniversity);
        try {
            Page<Intern> interns = internService.findInternsByAreaUniversity(idAreaUniversity, pageable);
            logger.info("Practicantes encontrados para área universitaria con ID: {}", idAreaUniversity);
            return ResponseEntity.ok(ResponseUtils.successResponse("Practicantes encontrados", interns));
        } catch (Exception ex) {
            logger.error("Error al buscar practicantes por área universitaria con ID: {}. Error: {}", idAreaUniversity, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtils.errorResponse("Error al buscar practicantes"));
        }
    }

    @GetMapping("/total")
    public Long getTotalInterns() {
        logger.info("Solicitud para obtener el total de practicantes");
        try {
            Long totalInterns = internService.getTotalInterns();
            logger.info("Total de practicantes: {}", totalInterns);
            return totalInterns;
        } catch (Exception ex) {
            logger.error("Error al obtener el total de practicantes: {}", ex.getMessage());
            return 0L;
        }
    }

    @PutMapping("/update/{idIntern}")
    public ResponseEntity<?> updateIntern(@PathVariable String idIntern, @RequestBody Intern intern) {
        logger.info("Solicitud para actualizar practicante con ID: {}", idIntern);
        try {
            Intern updatedIntern = internService.updateIntern(idIntern, intern);
            logger.info("Practicante actualizado exitosamente: {}", updatedIntern);
            return ResponseEntity.ok(ResponseUtils.successResponse("Practicante actualizado exitosamente", updatedIntern));
        } catch (RuntimeException ex) {
            logger.error("Error al actualizar el practicante con ID: {}. Error: {}", idIntern, ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtils.errorResponse(ex.getMessage()));
        }
    }

    @DeleteMapping("/delete/{idIntern}")
    public ResponseEntity<?> deleteIntern(@PathVariable String idIntern) {
        logger.info("Solicitud para eliminar practicante con ID: {}", idIntern);
        try {
            internService.deleteIntern(idIntern);
            logger.info("Practicante eliminado exitosamente con ID: {}", idIntern);
            return ResponseEntity.ok(ResponseUtils.successResponse("Practicante eliminado exitosamente", null));
        } catch (RuntimeException ex) {
            logger.error("Error al eliminar el practicante con ID: {}. Error: {}", idIntern, ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtils.errorResponse(ex.getMessage()));
        }
    }

}
