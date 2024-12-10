package com.mpch.controlDeAsistencias_backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mpch.controlDeAsistencias_backend.model.Certificated;
import com.mpch.controlDeAsistencias_backend.model.Intern;
import com.mpch.controlDeAsistencias_backend.services.CertificatedService;
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
@RequestMapping("/certificated")
@CrossOrigin(origins = "http://localhost:4200")
public class CertificatedController {

    @Autowired
    private CertificatedService certificatedService;

    private static final Logger logger = LoggerFactory.getLogger(CertificatedController.class);

    @PostMapping("/generate")
    public ResponseEntity<?> generateCertificate(@RequestParam String dni) {
        logger.info("Solicitud para generar certificado para DNI: {}", dni);
        try {
            Certificated certificated = certificatedService.generateCertificated(dni);
            logger.info("Certificado generado con éxito para DNI: {}", dni);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ResponseUtils.successResponse("Certificado generado con éxito", certificated));
        } catch (RuntimeException ex) {
            logger.error("Error al generar certificado para DNI: {} con error: {}", dni, ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseUtils.errorResponse(ex.getMessage()));
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getCertificateById(@PathVariable UUID id) {
        logger.info("Solicitud para obtener certificado con ID: {}", id);
        try {
            Certificated certificated = certificatedService.findById(id);
            logger.info("Certificado encontrado con ID: {}", id);
            return ResponseEntity.ok(ResponseUtils.successResponse("Certificado encontrado", certificated));
        } catch (RuntimeException ex) {
            logger.error("Certificado no encontrado con ID: {}. Error: {}", id, ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtils.errorResponse(ex.getMessage()));
        }
    }

    @GetMapping("/{dni}")
    public ResponseEntity<?> getCertificateByDni(@PathVariable String dni) {
        logger.info("Solicitud para obtener certificado para DNI: {}", dni);
        try {
            Certificated certificated = certificatedService.findCertificateByIntern(dni);
            logger.info("Certificado encontrado para DNI: {}", dni);
            return ResponseEntity.ok(ResponseUtils.successResponse("Certificado encontrado", certificated));
        } catch (RuntimeException ex) {
            logger.error("Certificado no encontrado para DNI: {}. Error: {}", dni, ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtils.errorResponse(ex.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllCertificates(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        logger.info("Solicitud para obtener todos los certificados con paginación: Página {}, Tamaño {}", pageable.getPageNumber(), pageable.getPageSize());
        try {
            Page<Certificated> certificates = certificatedService.findAllCertificates(pageable);
            logger.info("Certificados obtenidos con éxito. Total de certificados: {}", certificates.getTotalElements());
            return ResponseEntity.ok(ResponseUtils.successResponse("Certificados obtenidos con éxito", certificates));
        } catch (Exception ex) {
            logger.error("Error al obtener certificados: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtils.errorResponse("Error al obtener certificados"));
        }
    }

    @GetMapping("/eligible")
    public ResponseEntity<?> getEligibleInterns(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        logger.info("Solicitud para obtener practicantes elegibles para certificación con paginación: Página {}, Tamaño {}", pageable.getPageNumber(), pageable.getPageSize());
        try {
            Page<Intern> eligibleInterns = certificatedService.findEligibleInternsForCertification(pageable);
            logger.info("Practicantes elegibles obtenidos con éxito. Total de practicantes: {}", eligibleInterns.getTotalElements());
            return ResponseEntity.ok(ResponseUtils.successResponse("Practicantes elegibles obtenidos con éxito", eligibleInterns));
        } catch (Exception ex) {
            logger.error("Error al obtener practicantes elegibles: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtils.errorResponse("Error al obtener practicantes elegibles"));
        }
    }
}
