package com.mpch.controlDeAsistencias_backend.controller;

import com.mpch.controlDeAsistencias_backend.model.Role;
import com.mpch.controlDeAsistencias_backend.services.RoleService;
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
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/add")
    public ResponseEntity<?> addRole(@RequestBody Role role) {
        try {
            Role createdRole = roleService.saveRole(role);
            return ResponseEntity.status(HttpStatus.CREATED).body(successResponse("Rol creado exitosamente", createdRole));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse("Error al crear el rol"));
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getRoleById(@PathVariable Long id) {
        try {
            Role role = roleService.findRoleById(id);
            return ResponseEntity.ok(successResponse("Rol encontrado", role));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse(ex.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllRoles(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            Page<Role> roles = roleService.getAllRoles(pageable);
            return ResponseEntity.ok(successResponse("Roles obtenidos exitosamente", roles));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse("Error al obtener los roles"));
        }
    }

    @GetMapping("/total")
    public Long getTotalRoles() {
        return roleService.getTotalRoles();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateRole(@PathVariable Long id, @RequestBody Role role) {
        try {
            Role updatedRole = roleService.updateRole(id, role);
            return ResponseEntity.ok(successResponse("Rol actualizado exitosamente", updatedRole));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse("Error al actualizar el rol"));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable Long id) {
        try {
            roleService.deleteRole(id);
            return ResponseEntity.ok(successResponse("Rol eliminado exitosamente", null));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse("Error al eliminar el rol"));
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
