package com.mpch.controlDeAsistencias_backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mpch.controlDeAsistencias_backend.model.Role;
import com.mpch.controlDeAsistencias_backend.services.RoleService;
import com.mpch.controlDeAsistencias_backend.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
@CrossOrigin(origins = "http://localhost:4200")
public class RoleController {

    @Autowired
    private RoleService roleService;

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @PostMapping("/add")
    public ResponseEntity<?> addRole(@RequestBody Role role) {
        try {
            logger.info("Iniciando la creación del rol: {}", role.getName());
            Role createdRole = roleService.saveRole(role);
            logger.info("Rol creado exitosamente con ID: {}", createdRole.getIdRole());
            return ResponseEntity.status(HttpStatus.CREATED).body(ResponseUtils.successResponse("Rol creado exitosamente", createdRole));
        } catch (RuntimeException ex) {
            logger.error("Error al crear el rol: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseUtils.errorResponse(ex.getMessage()));
        } catch (Exception ex) {
            logger.error("Error inesperado al crear el rol: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseUtils.errorResponse("Error al crear el rol"));
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getRoleById(@PathVariable Long id) {
        try {
            logger.info("Buscando rol con ID: {}", id);
            Role role = roleService.findRoleById(id);
            logger.info("Rol encontrado: {}", role.getName());
            return ResponseEntity.ok(ResponseUtils.successResponse("Rol encontrado", role));
        } catch (RuntimeException ex) {
            logger.error("Rol no encontrado con ID: {} - {}", id, ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseUtils.errorResponse(ex.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllRoles(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            logger.info("Obteniendo lista de roles, página: {}, tamaño: {}", pageable.getPageNumber(), pageable.getPageSize());
            Page<Role> roles = roleService.getAllRoles(pageable);
            logger.info("Roles obtenidos: {}", roles.getTotalElements());
            return ResponseEntity.ok(ResponseUtils.successResponse("Roles obtenidos exitosamente", roles));
        } catch (Exception ex) {
            logger.error("Error al obtener los roles: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseUtils.errorResponse("Error al obtener los roles"));
        }
    }

    @GetMapping("/total")
    public Long getTotalRoles() {
        long totalRoles = roleService.getTotalRoles();
        logger.info("Total de roles: {}", totalRoles);
        return totalRoles;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateRole(@PathVariable Long id, @RequestBody Role role) {
        try {
            logger.info("Actualizando rol con ID: {}", id);
            Role updatedRole = roleService.updateRole(id, role);
            logger.info("Rol actualizado con ID: {}", updatedRole.getIdRole());
            return ResponseEntity.ok(ResponseUtils.successResponse("Rol actualizado exitosamente", updatedRole));
        } catch (RuntimeException ex) {
            logger.error("Error al actualizar el rol con ID: {} - {}", id, ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseUtils.errorResponse(ex.getMessage()));
        } catch (Exception ex) {
            logger.error("Error inesperado al actualizar el rol con ID: {} - {}", id, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseUtils.errorResponse("Error al actualizar el rol"));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable Long id) {
        try {
            logger.info("Eliminando rol con ID: {}", id);
            roleService.deleteRole(id);
            logger.info("Rol eliminado con ID: {}", id);
            return ResponseEntity.ok(ResponseUtils.successResponse("Rol eliminado exitosamente", null));
        } catch (RuntimeException ex) {
            logger.error("Error al eliminar el rol con ID: {} - {}", id, ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseUtils.errorResponse(ex.getMessage()));
        } catch (Exception ex) {
            logger.error("Error inesperado al eliminar el rol con ID: {} - {}", id, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseUtils.errorResponse("Error al eliminar el rol"));
        }
    }
}
