package com.mpch.controlDeAsistencias_backend.controller;

import com.mpch.controlDeAsistencias_backend.model.Certificated;
import com.mpch.controlDeAsistencias_backend.services.CertificatedService;
import com.mpch.controlDeAsistencias_backend.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/certificated")
@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin("*")
public class CertificatedController {

    @Autowired
    private CertificatedService certificatedService;

    @PostMapping("/generate")
    public ResponseEntity<?> generateCertificate(@RequestParam String dni) {
        try {
            Certificated certificated = certificatedService.generateCertificated(dni);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ResponseUtils.successResponse("Certificado generado con éxito", certificated));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseUtils.errorResponse(ex.getMessage()));
        }
    }

    @GetMapping("/{dni}")
    public ResponseEntity<?> getCertificateByDni(@PathVariable String dni) {
        try {
            Certificated certificated = certificatedService.findCertificateByIntern(dni);
            return ResponseEntity.ok(ResponseUtils.successResponse("Certificado encontrado", certificated));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtils.errorResponse(ex.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllCertificates(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Page<Certificated> certificates = certificatedService.findAllCertificates(pageable);
            return ResponseEntity.ok(ResponseUtils.successResponse("Certificados obtenidos con éxito", certificates));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtils.errorResponse("Error al obtener certificados"));
        }
    }

}
