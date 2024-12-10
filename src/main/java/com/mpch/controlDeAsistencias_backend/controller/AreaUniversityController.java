package com.mpch.controlDeAsistencias_backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mpch.controlDeAsistencias_backend.model.AreaUniversity;
import com.mpch.controlDeAsistencias_backend.services.AreaUniversityService;
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
@RequestMapping("/area-university")
@CrossOrigin(origins = "http://localhost:4200")
public class AreaUniversityController {

    @Autowired
    private AreaUniversityService areaUniversityService;

    private static final Logger logger = LoggerFactory.getLogger(AreaUniversityController.class);

    @PostMapping("/add")
    public ResponseEntity<?> addAreaUniversity(@RequestBody AreaUniversity areaUniversity) {
        logger.info("Iniciando creación de asociación entre área y universidad con datos: {}", areaUniversity);
        try {
            AreaUniversity created = areaUniversityService.addAreaUniversity(areaUniversity);
            logger.info("Asociación creada exitosamente: {}", created);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ResponseUtils.successResponse("Asociación creada exitosamente", created));
        } catch (RuntimeException ex) {
            logger.error("Error al crear la asociación: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseUtils.errorResponse(ex.getMessage()));
        } catch (Exception ex) {
            logger.error("Error al crear la asociación: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtils.errorResponse("Error al asociar universidad con área"));
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getAreaUniversityById(@PathVariable UUID id) {
        logger.info("Buscando asociación con ID: {}", id);
        try {
            AreaUniversity areaUniversity = areaUniversityService.getAreaUniversityById(id);
            logger.info("Asociación encontrada: {}", areaUniversity);
            return ResponseEntity.ok(ResponseUtils.successResponse("Relación encontrada", areaUniversity));
        } catch (RuntimeException ex) {
            logger.error("Asociación no encontrada: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtils.errorResponse(ex.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllAreaUniversities(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        logger.info("Obteniendo lista de asociaciones de áreas y universidades, página: {}", pageable.getPageNumber());
        try {
            Page<AreaUniversity> areaUniversities = areaUniversityService.getAllAreaUniversities(pageable);
            logger.info("Lista de asociaciones obtenida exitosamente");
            return ResponseEntity.ok(ResponseUtils.successResponse("Asociaciones obtenidas exitosamente", areaUniversities));
        } catch (Exception ex) {
            logger.error("Error al obtener asociaciones: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtils.errorResponse("Error al obtener asociaciones"));
        }
    }

    @GetMapping("/search/{areaName}/{universityName}")
    public ResponseEntity<?> searchByAreaOrUniversity(
            @PathVariable String areaName,
            @PathVariable String universityName,
            @PageableDefault(size = 10, page = 0) Pageable pageable
    ) {
        logger.info("Buscando asociaciones por área: {} o universidad: {}", areaName, universityName);
        try {
            Page<AreaUniversity> results = areaUniversityService.searchByAreaOrUniversity(areaName, universityName, pageable);
            logger.info("Asociaciones encontradas: {}", results.getTotalElements());
            return ResponseEntity.ok(ResponseUtils.successResponse("Resultados de búsqueda obtenidos", results));
        } catch (Exception ex) {
            logger.error("Error al buscar asociaciones: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtils.errorResponse("Error al buscar asociaciones"));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAreaUniversity(@PathVariable UUID id) {
        logger.info("Eliminando asociación con ID: {}", id);
        try {
            areaUniversityService.deleteAreaUniversity(id);
            logger.info("Asociación eliminada exitosamente");
            return ResponseEntity.ok(ResponseUtils.successResponse("Asociación eliminada exitosamente", null));
        } catch (RuntimeException ex) {
            logger.error("Error al eliminar la asociación: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseUtils.errorResponse(ex.getMessage()));
        } catch (Exception ex) {
            logger.error("Error al eliminar la asociación: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtils.errorResponse("Error al eliminar la asociación"));
        }
    }

    @GetMapping("/university/{idUniversity}")
    public ResponseEntity<?> getAreasByUniversity(
            @PathVariable Long idUniversity,
            @PageableDefault(size = 10, page = 0) Pageable pageable
    ) {
        logger.info("Buscando áreas asociadas a la universidad con ID: {}", idUniversity);
        try {
            Page<AreaUniversity> areas = areaUniversityService.getAreasByUniversity(idUniversity, pageable);
            logger.info("Áreas asociadas a la universidad obtenidas exitosamente");
            return ResponseEntity.ok(ResponseUtils.successResponse("Áreas asociadas obtenidas exitosamente", areas));
        } catch (Exception ex) {
            logger.error("Error al obtener áreas asociadas: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtils.errorResponse("Error al obtener las áreas asociadas"));
        }
    }

    @GetMapping("/area/{idArea}")
    public ResponseEntity<?> getUniversitiesByArea(
            @PathVariable Long idArea,
            @PageableDefault(size = 10, page = 0) Pageable pageable
    ) {
        logger.info("Buscando universidades asociadas al área con ID: {}", idArea);
        try {
            Page<AreaUniversity> universities = areaUniversityService.getUniversitiesByArea(idArea, pageable);
            logger.info("Universidades asociadas al área obtenidas exitosamente");
            return ResponseEntity.ok(ResponseUtils.successResponse("Universidades asociadas obtenidas exitosamente", universities));
        } catch (Exception ex) {
            logger.error("Error al obtener universidades asociadas: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtils.errorResponse("Error al obtener las universidades asociadas"));
        }
    }

}
