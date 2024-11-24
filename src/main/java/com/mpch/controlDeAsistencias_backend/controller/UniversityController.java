package com.mpch.controlDeAsistencias_backend.controller;

import com.mpch.controlDeAsistencias_backend.model.University;
import com.mpch.controlDeAsistencias_backend.services.UniversityService;
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
@RequestMapping("/university")
public class UniversityController {

    @Autowired
    private UniversityService universityService;

    @PostMapping("/add")
    public ResponseEntity<?> addUniversity(@RequestBody University university) {
        try {
            University createdUniversity = universityService.createUniversity(university);
            return ResponseEntity.status(HttpStatus.CREATED).body(successResponse("Universidad creada exitosamente", createdUniversity));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse("Error al crear la universidad"));
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getUniversityById(@PathVariable Long id) {
        try {
            University university = universityService.getUniversityById(id);
            return ResponseEntity.ok(successResponse("Universidad encontrada", university));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse(ex.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllUniversities(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Page<University> universities = universityService.getAllUniversities(pageable);
            return ResponseEntity.ok(successResponse("Universidades obtenidas exitosamente", universities));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse("Error al obtener las universidades"));
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> searchUniversityByName(@PathVariable String name, Pageable pageable) {
        try {
            Page<University> universities = universityService.searchUniversityByName(name, pageable);
            return ResponseEntity.ok(successResponse("Resultados de búsqueda obtenidos", universities));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse("Error al buscar las universidades"));
        }
    }

    @GetMapping("/total")
    public Long getTotalUniversities() {
        return universityService.getTotalUniversities();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUniversity(@PathVariable Long id, @RequestBody University university) {
        try {
            University updatedUniversity = universityService.updateUniversity(id, university);
            return ResponseEntity.ok(successResponse("Universidad actualizada exitosamente", updatedUniversity));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse("Error al actualizar la universidad"));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUniversity(@PathVariable Long id) {
        try {
            universityService.deleteUniversity(id);
            return ResponseEntity.ok(successResponse("Universidad eliminada exitosamente", null));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse("Error al eliminar la universidad"));
        }
    }

    private Map<String, Object> successResponse(String message, Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("data", data);
        return response;
    }

    private Map<String, String> errorResponse(String message) {
        Map<String, String> response = new HashMap<>();
        response.put("error", message);
        return response;
    }
}
