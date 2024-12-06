package com.mpch.controlDeAsistencias_backend.controller;

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

    @PostMapping("/add")
    public ResponseEntity<?> addIntern(@RequestBody Intern intern) {
        try {
            // System.out.println("Total Hours: " + intern.getTotalHours());
            Intern createdIntern = internService.saveIntern(intern);
            return ResponseEntity.status(HttpStatus.CREATED).
                    body(ResponseUtils.successResponse("Practicante creado exitosamente", createdIntern));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(ResponseUtils.errorResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body(ResponseUtils.errorResponse("Error al crear el practicante"));
        }
    }

    @GetMapping("/id/{idIntern}")
    public ResponseEntity<?> getInternById(@PathVariable String idIntern) {
        try {
            Intern intern = internService.findInternById(idIntern);
            return ResponseEntity.ok(ResponseUtils.successResponse("Practicante encontrado", intern));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseUtils.errorResponse(ex.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllInterns(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Page<Intern> interns = internService.findAllInterns(pageable);
            return ResponseEntity.ok(ResponseUtils.successResponse("Lista de practicantes obtenida exitosamente", interns));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseUtils.errorResponse("Error al obtener la lista de practicantes"));
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> searchInternsByName(@PathVariable String name, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Page<Intern> interns = internService.searchInternsByName(name, pageable);
            return ResponseEntity.ok(ResponseUtils.successResponse("Practicantes encontrados", interns));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseUtils.errorResponse("Error al buscar practicantes"));
        }
    }

    @GetMapping("/area/{area}")
    public ResponseEntity<?> findInternsByArea(@PathVariable String area, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Page<Intern> interns = internService.findInternsByArea(area, pageable);
            return ResponseEntity.ok(ResponseUtils.successResponse("Practicantes encontrados", interns));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseUtils.errorResponse("Error al buscar practicantes"));
        }
    }

    @GetMapping("/university/{university}")
    public ResponseEntity<?> findInternsByUniversity(@PathVariable String university, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Page<Intern> interns = internService.findInternsByUniversity(university, pageable);
            return ResponseEntity.ok(ResponseUtils.successResponse("Practicantes encontrados", interns));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseUtils.errorResponse("Error al buscar practicantes"));
        }
    }

    @GetMapping("/area-university/{idAreaUniversity}")
    public ResponseEntity<?> findInternsByAreaUniversity(@PathVariable UUID idAreaUniversity, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Page<Intern> interns = internService.findInternsByAreaUniversity(idAreaUniversity, pageable);
            return ResponseEntity.ok(ResponseUtils.successResponse("Practicantes encontrados", interns));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseUtils.errorResponse("Error al buscar practicantes"));
        }
    }

    @GetMapping("/total")
    public Long getTotalInterns() {
        return internService.getTotalInterns();
    }

    @PutMapping("/update/{idIntern}")
    public ResponseEntity<?> updateIntern(@PathVariable String idIntern, @RequestBody Intern intern) {
        try {
            Intern updatedIntern = internService.updateIntern(idIntern, intern);
            return ResponseEntity.ok(ResponseUtils.successResponse("Practicante actualizado exitosamente", updatedIntern));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseUtils.errorResponse(ex.getMessage()));
        }
    }

    @DeleteMapping("/delete/{idIntern}")
    public ResponseEntity<?> deleteIntern(@PathVariable String idIntern) {
        try {
            internService.deleteIntern(idIntern);
            return ResponseEntity.ok(ResponseUtils.successResponse("Practicante eliminado exitosamente", null));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseUtils.errorResponse(ex.getMessage()));
        }
    }
    
}
