package com.mpch.controlDeAsistencias_backend.controller;

import com.mpch.controlDeAsistencias_backend.model.Area;
import com.mpch.controlDeAsistencias_backend.services.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/area")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @PostMapping("/add")
    public ResponseEntity<?> addArea(@RequestBody Area area) {
        try {
            Area createdArea = areaService.addArea(area);
            return ResponseEntity.status(HttpStatus.CREATED).
                    body(successResponse("Área creada exitosamente", createdArea));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(errorResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body(errorResponse("Error al crear el área"));
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getAreaById(@PathVariable Long id) {
        try {
            Area area = areaService.getAreaById(id);
            return ResponseEntity.ok(successResponse("Área encontrada", area));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse(ex.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllAreas(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Page<Area> areas = areaService.getAllAreas(pageable);
            return ResponseEntity.ok(successResponse("Áreas obtenidas exitosamente", areas));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse("Error al obtener las áreas"));
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> searchAreaByName(@PathVariable String name, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Page<Area> areas = areaService.searchAreaByName(name, pageable);
            return ResponseEntity.ok(successResponse("Resultados de búsqueda obtenidos", areas));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse("Error al buscar las áreas"));
        }
    }

    @GetMapping("/total")
    public Long getTotalAreas() {
        return areaService.getTotalAreas();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateArea(@PathVariable Long id, @RequestBody Area area) {
        try {
            Area updatedArea = areaService.updateArea(id, area);
            return ResponseEntity.ok(successResponse("Área actualizada exitosamente", updatedArea));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse("Error al actualizar el área"));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteArea(@PathVariable Long id) {
        try {
            areaService.deleteArea(id);
            return ResponseEntity.ok(successResponse("Área eliminada exitosamente", null));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse("Error al eliminar el área"));
        }
    }

    // Respuesta de éxito
    private Map<String, Object> successResponse(String message, Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("data", data);
        return response;
    }

    // Respuesta de error
    private Map<String, String> errorResponse(String message) {
        Map<String, String> response = new HashMap<>();
        response.put("error", message);
        return response;
    }

}
