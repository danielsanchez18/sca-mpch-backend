package com.mpch.controlDeAsistencias_backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mpch.controlDeAsistencias_backend.model.Security;
import com.mpch.controlDeAsistencias_backend.services.SecurityService;
import com.mpch.controlDeAsistencias_backend.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/security")
@CrossOrigin(origins = "http://localhost:4200")
public class SecurityController {

    @Autowired
    private SecurityService securityService;

    private static final Logger logger = LoggerFactory.getLogger(SecurityController.class);

    @PostMapping("/add")
    public ResponseEntity<?> addSecurity(@RequestBody Security security) {
        try {
            logger.info("Iniciando la creación de la seguridad: {}", security.getUser().getName());
            Security createdSecurity = securityService.saveSecurity(security);
            logger.info("Seguridad creada exitosamente con ID: {}", createdSecurity.getIdSecurity());
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ResponseUtils.successResponse("Seguridad creado exitosamente", createdSecurity));
        } catch (RuntimeException ex) {
            logger.error("Error al crear la seguridad: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseUtils.errorResponse(ex.getMessage()));
        } catch (Exception ex) {
            logger.error("Error inesperado al crear la seguridad: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtils.errorResponse("Error al crear el security"));
        }
    }

    @GetMapping("/id/{idSecurity}")
    public ResponseEntity<?> getSecurityById(@PathVariable String idSecurity) {
        try {
            logger.info("Buscando seguridad con ID: {}", idSecurity);
            Security security = securityService.getSecurityById(idSecurity);
            logger.info("Seguridad encontrada: {}", security.getUser().getName());
            return ResponseEntity.ok(ResponseUtils.successResponse("Seguridad encontrado", security));
        } catch (RuntimeException ex) {
            logger.error("Seguridad no encontrada con ID: {} - {}", idSecurity, ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseUtils.errorResponse(ex.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllSecurities(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            logger.info("Obteniendo lista de seguridades, página: {}, tamaño: {}", pageable.getPageNumber(), pageable.getPageSize());
            Page<Security> securities = securityService.getAllSecurities(pageable);
            logger.info("Seguridades obtenidas: {}", securities.getTotalElements());
            return ResponseEntity.ok(ResponseUtils.successResponse("Lista de seguridades obtenida exitosamente", securities));
        } catch (Exception ex) {
            logger.error("Error al obtener la lista de seguridades: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtils.errorResponse("Error al obtener la lista de seguridades"));
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> searchSecuritiesByName(@PathVariable String name, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            logger.info("Buscando seguridades por nombre: {}", name);
            Page<Security> securities = securityService.searchSecuritiesByName(name, pageable);
            logger.info("Seguridades encontradas con nombre: {}", securities.getTotalElements());
            return ResponseEntity.ok(ResponseUtils.successResponse("Seguridades encontrados", securities));
        } catch (Exception ex) {
            logger.error("Error al buscar seguridades por nombre: {} - {}", name, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtils.errorResponse("Error al buscar los seguridades"));
        }
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<?> searchSecuritiesByDni(@PathVariable String dni, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            logger.info("Buscando seguridades por DNI: {}", dni);
            Page<Security> securities = securityService.searchSecuritiessByDni(dni, pageable);
            logger.info("Seguridades encontradas con DNI: {}", securities.getTotalElements());
            return ResponseEntity.ok(ResponseUtils.successResponse("Seguridades encontrados", securities));
        } catch (Exception ex) {
            logger.error("Error al buscar seguridades por DNI: {} - {}", dni, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtils.errorResponse("Error al buscar los Seguridades"));
        }
    }

    @GetMapping("/total")
    public Long getTotalSecurities() {
        long totalSecurities = securityService.getTotalSecurities();
        logger.info("Total de seguridades: {}", totalSecurities);
        return totalSecurities;
    }

    @PutMapping("/update/{idSecurity}")
    public ResponseEntity<?> updateSecurity(@PathVariable String idSecurity, @RequestBody Security security) {
        try {
            logger.info("Actualizando seguridad con ID: {}", idSecurity);
            Security updatedSecurity = securityService.updateSecurity(idSecurity, security);
            logger.info("Seguridad actualizada con ID: {}", updatedSecurity.getIdSecurity());
            return ResponseEntity.ok(ResponseUtils.successResponse("Seguridad actualizado exitosamente", updatedSecurity));
        } catch (RuntimeException ex) {
            logger.error("Error al actualizar la seguridad con ID: {} - {}", idSecurity, ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseUtils.errorResponse(ex.getMessage()));
        }
    }

    @DeleteMapping("/delete/{idSecurity}")
    public ResponseEntity<?> deleteSecurity(@PathVariable String idSecurity) {
        try {
            logger.info("Eliminando seguridad con ID: {}", idSecurity);
            securityService.deleteSecurity(idSecurity);
            logger.info("Seguridad eliminada con ID: {}", idSecurity);
            return ResponseEntity.ok(ResponseUtils.successResponse("Seguridad eliminado exitosamente", null));
        } catch (RuntimeException ex) {
            logger.error("Error al eliminar la seguridad con ID: {} - {}", idSecurity, ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseUtils.errorResponse(ex.getMessage()));
        }
    }
}