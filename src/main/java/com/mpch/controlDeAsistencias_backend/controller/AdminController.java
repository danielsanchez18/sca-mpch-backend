package com.mpch.controlDeAsistencias_backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mpch.controlDeAsistencias_backend.model.Admin;
import com.mpch.controlDeAsistencias_backend.services.AdminService;
import com.mpch.controlDeAsistencias_backend.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {

    @Autowired
    private AdminService adminService;

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @PostMapping("/add")
    public ResponseEntity<?> addAdmin(@RequestBody Admin admin) {
        logger.info("Iniciando creación de administrador con datos: {}", admin);
        try {
            Admin createdAdmin = adminService.saveAdmin(admin);
            logger.info("Administrador creado exitosamente: {}", createdAdmin);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ResponseUtils.successResponse("Administrador creado exitosamente", createdAdmin));
        } catch (RuntimeException ex) {
            logger.error("Error al crear el administrador: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseUtils.errorResponse(ex.getMessage()));
        } catch (Exception ex) {
            logger.error("Error al crear el administrador: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtils.errorResponse("Error al crear el administrador"));
        }
    }

    @GetMapping("/id/{idAdmin}")
    public ResponseEntity<?> getAdminById(@PathVariable String idAdmin) {
        logger.info("Buscando administrador con ID: {}", idAdmin);
        try {
            Admin admin = adminService.getAdminById(idAdmin);
            logger.info("Administrador encontrado: {}", admin);
            return ResponseEntity.ok(ResponseUtils.successResponse("Administrador encontrado", admin));
        } catch (RuntimeException ex) {
            logger.error("Administrador no encontrado: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtils.errorResponse(ex.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllAdmins(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        logger.info("Obteniendo lista de administradores, página: {}", pageable.getPageNumber());
        try {
            Page<Admin> admins = adminService.getAllAdmins(pageable);
            logger.info("Lista de administradores obtenida exitosamente");
            return ResponseEntity.ok(ResponseUtils.successResponse("Lista de administradores obtenida exitosamente", admins));
        } catch (Exception ex) {
            logger.error("Error al obtener la lista de administradores: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtils.errorResponse("Error al obtener la lista de administradores"));
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> searchAdminsByName(@PathVariable String name, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        logger.info("Buscando administradores por nombre: {}", name);
        try {
            Page<Admin> admins = adminService.searchAdminsByName(name, pageable);
            logger.info("Administradores encontrados: {}", admins.getTotalElements());
            return ResponseEntity.ok(ResponseUtils.successResponse("Administradores encontrados", admins));
        } catch (Exception ex) {
            logger.error("Error al buscar administradores por nombre: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtils.errorResponse("Error al buscar los administradores"));
        }
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<?> searchAdminsByDni(@PathVariable String dni, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        logger.info("Buscando administradores por DNI: {}", dni);
        try {
            Page<Admin> admins = adminService.searchAdminsByDni(dni, pageable);
            logger.info("Administradores encontrados: {}", admins.getTotalElements());
            return ResponseEntity.ok(ResponseUtils.successResponse("Administradores encontrados", admins));
        } catch (Exception ex) {
            logger.error("Error al buscar administradores por DNI: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtils.errorResponse("Error al buscar los administradores"));
        }
    }

    @GetMapping("/total")
    public Long getTotalAdmins() {
        logger.info("Obteniendo el total de administradores");
        return adminService.getTotalAdmins();
    }

    @PutMapping("/update/{idAdmin}")
    public ResponseEntity<?> updateAdmin(@PathVariable String idAdmin, @RequestBody Admin admin) {
        logger.info("Actualizando administrador con ID: {} y nuevos datos: {}", idAdmin, admin);
        try {
            Admin updatedAdmin = adminService.updateAdmin(idAdmin, admin);
            logger.info("Administrador actualizado exitosamente: {}", updatedAdmin);
            return ResponseEntity.ok(ResponseUtils.successResponse("Administrador actualizado exitosamente", updatedAdmin));
        } catch (RuntimeException ex) {
            logger.error("Error al actualizar el administrador: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtils.errorResponse(ex.getMessage()));
        }
    }

    @DeleteMapping("/delete/{idAdmin}")
    public ResponseEntity<?> deleteAdmin(@PathVariable String idAdmin) {
        logger.info("Eliminando administrador con ID: {}", idAdmin);
        try {
            adminService.deleteAdmin(idAdmin);
            logger.info("Administrador eliminado exitosamente");
            return ResponseEntity.ok(ResponseUtils.successResponse("Administrador eliminado exitosamente", null));
        } catch (RuntimeException ex) {
            logger.error("Error al eliminar el administrador: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtils.errorResponse(ex.getMessage()));
        }
    }
}