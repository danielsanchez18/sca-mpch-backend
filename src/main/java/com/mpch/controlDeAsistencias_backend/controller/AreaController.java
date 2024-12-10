package com.mpch.controlDeAsistencias_backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mpch.controlDeAsistencias_backend.model.Area;
import com.mpch.controlDeAsistencias_backend.services.AreaService;
import com.mpch.controlDeAsistencias_backend.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/area")
@CrossOrigin(origins = "http://localhost:4200")
public class AreaController {

    @Autowired
    private AreaService areaService;

    private static final Logger logger = LoggerFactory.getLogger(AreaController.class);

    @PostMapping("/add")
    public ResponseEntity<?> addArea(@RequestBody Area area) {
        logger.info("Iniciando creación de área con datos: {}", area);
        try {
            Area createdArea = areaService.addArea(area);
            logger.info("Área creada exitosamente: {}", createdArea);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ResponseUtils.successResponse("Área creada exitosamente", createdArea));
        } catch (RuntimeException ex) {
            logger.error("Error al crear el área: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseUtils.errorResponse(ex.getMessage()));
        } catch (Exception ex) {
            logger.error("Error al crear el área: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtils.errorResponse("Error al crear el área"));
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getAreaById(@PathVariable Long id) {
        logger.info("Buscando área con ID: {}", id);
        try {
            Area area = areaService.getAreaById(id);
            logger.info("Área encontrada: {}", area);
            return ResponseEntity.ok(ResponseUtils.successResponse("Área encontrada", area));
        } catch (RuntimeException ex) {
            logger.error("Área no encontrada: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtils.errorResponse(ex.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllAreas(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        logger.info("Obteniendo lista de áreas, página: {}", pageable.getPageNumber());
        try {
            Page<Area> areas = areaService.getAllAreas(pageable);
            logger.info("Lista de áreas obtenida exitosamente");
            return ResponseEntity.ok(ResponseUtils.successResponse("Áreas obtenidas exitosamente", areas));
        } catch (Exception ex) {
            logger.error("Error al obtener las áreas: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtils.errorResponse("Error al obtener las áreas"));
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> searchAreaByName(@PathVariable String name, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        logger.info("Buscando áreas por nombre: {}", name);
        try {
            Page<Area> areas = areaService.searchAreaByName(name, pageable);
            logger.info("Áreas encontradas: {}", areas.getTotalElements());
            return ResponseEntity.ok(ResponseUtils.successResponse("Resultados de búsqueda obtenidos", areas));
        } catch (Exception ex) {
            logger.error("Error al buscar áreas por nombre: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtils.errorResponse("Error al buscar las áreas"));
        }
    }

    @GetMapping("/total")
    public Long getTotalAreas() {
        logger.info("Obteniendo el total de áreas");
        return areaService.getTotalAreas();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateArea(@PathVariable Long id, @RequestBody Area area) {
        logger.info("Actualizando área con ID: {} y nuevos datos: {}", id, area);
        try {
            Area updatedArea = areaService.updateArea(id, area);
            logger.info("Área actualizada exitosamente: {}", updatedArea);
            return ResponseEntity.ok(ResponseUtils.successResponse("Área actualizada exitosamente", updatedArea));
        } catch (RuntimeException ex) {
            logger.error("Error al actualizar el área: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseUtils.errorResponse(ex.getMessage()));
        } catch (Exception ex) {
            logger.error("Error al actualizar el área: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtils.errorResponse("Error al actualizar el área"));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteArea(@PathVariable Long id) {
        logger.info("Eliminando área con ID: {}", id);
        try {
            areaService.deleteArea(id);
            logger.info("Área eliminada exitosamente");
            return ResponseEntity.ok(ResponseUtils.successResponse("Área eliminada exitosamente", null));
        } catch (RuntimeException ex) {
            logger.error("Error al eliminar el área: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseUtils.errorResponse(ex.getMessage()));
        } catch (Exception ex) {
            logger.error("Error al eliminar el área: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtils.errorResponse("Error al eliminar el área"));
        }
    }
}
