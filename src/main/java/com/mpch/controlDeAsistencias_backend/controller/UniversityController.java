package com.mpch.controlDeAsistencias_backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mpch.controlDeAsistencias_backend.model.University;
import com.mpch.controlDeAsistencias_backend.services.UniversityService;
import com.mpch.controlDeAsistencias_backend.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/university")
@CrossOrigin(origins = "http://localhost:4200")
public class UniversityController {

    @Autowired
    private UniversityService universityService;

    private static final Logger logger = LoggerFactory.getLogger(UniversityController.class);

    @PostMapping("/add")
    public ResponseEntity<?> addUniversity(@RequestBody University university) {
        try {
            logger.info("Iniciando la creación de la universidad: {}", university.getName());
            University createdUniversity = universityService.createUniversity(university);
            logger.info("Universidad creada exitosamente con ID: {}", createdUniversity.getIdUniversity());
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ResponseUtils.successResponse("Universidad creada exitosamente", createdUniversity));
        } catch (RuntimeException ex) {
            logger.error("Error al crear la universidad: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseUtils.errorResponse(ex.getMessage()));
        } catch (Exception ex) {
            logger.error("Error inesperado al crear la universidad: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtils.errorResponse("Error al crear la universidad"));
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getUniversityById(@PathVariable Long id) {
        try {
            logger.info("Buscando universidad con ID: {}", id);
            University university = universityService.getUniversityById(id);
            logger.info("Universidad encontrada: {}", university.getName());
            return ResponseEntity.ok(ResponseUtils.successResponse("Universidad encontrada", university));
        } catch (RuntimeException ex) {
            logger.error("Universidad no encontrada con ID: {} - {}", id, ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtils.errorResponse(ex.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllUniversities(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            logger.info("Obteniendo lista de universidades, página: {}, tamaño: {}", pageable.getPageNumber(), pageable.getPageSize());
            Page<University> universities = universityService.getAllUniversities(pageable);
            logger.info("Universidades obtenidas: {}", universities.getTotalElements());
            return ResponseEntity.ok(ResponseUtils.successResponse("Universidades obtenidas exitosamente", universities));
        } catch (Exception ex) {
            logger.error("Error al obtener las universidades: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtils.errorResponse("Error al obtener las universidades"));
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> searchUniversityByName(@PathVariable String name, Pageable pageable) {
        try {
            logger.info("Buscando universidades por nombre: {}", name);
            Page<University> universities = universityService.searchUniversityByName(name, pageable);
            logger.info("Resultados de búsqueda obtenidos para nombre: {}", universities.getTotalElements());
            return ResponseEntity.ok(ResponseUtils.successResponse("Resultados de búsqueda obtenidos", universities));
        } catch (Exception ex) {
            logger.error("Error al buscar universidades por nombre: {} - {}", name, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtils.errorResponse("Error al buscar las universidades"));
        }
    }

    @GetMapping("/total")
    public Long getTotalUniversities() {
        long totalUniversities = universityService.getTotalUniversities();
        logger.info("Total de universidades: {}", totalUniversities);
        return totalUniversities;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUniversity(@PathVariable Long id, @RequestBody University university) {
        try {
            logger.info("Actualizando universidad con ID: {}", id);
            University updatedUniversity = universityService.updateUniversity(id, university);
            logger.info("Universidad actualizada con ID: {}", updatedUniversity.getIdUniversity());
            return ResponseEntity.ok(ResponseUtils.successResponse("Universidad actualizada exitosamente", updatedUniversity));
        } catch (RuntimeException ex) {
            logger.error("Error al actualizar la universidad con ID: {} - {}", id, ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseUtils.errorResponse(ex.getMessage()));
        } catch (Exception ex) {
            logger.error("Error inesperado al actualizar la universidad con ID: {} - {}", id, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtils.errorResponse("Error al actualizar la universidad"));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUniversity(@PathVariable Long id) {
        try {
            logger.info("Eliminando universidad con ID: {}", id);
            universityService.deleteUniversity(id);
            logger.info("Universidad eliminada con ID: {}", id);
            return ResponseEntity.ok(ResponseUtils.successResponse("Universidad eliminada exitosamente", null));
        } catch (RuntimeException ex) {
            logger.error("Error al eliminar la universidad con ID: {} - {}", id, ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseUtils.errorResponse(ex.getMessage()));
        } catch (Exception ex) {
            logger.error("Error inesperado al eliminar la universidad con ID: {} - {}", id, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtils.errorResponse("Error al eliminar la universidad"));
        }
    }
}