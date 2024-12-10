package com.mpch.controlDeAsistencias_backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mpch.controlDeAsistencias_backend.model.User;
import com.mpch.controlDeAsistencias_backend.services.UserService;
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
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        try {
            logger.info("Iniciando la creación de usuario: {}", user.getName());
            User createdUser = userService.save(user);
            logger.info("Usuario creado exitosamente con ID: {}", createdUser.getIdUser());
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ResponseUtils.successResponse("Usuario creado exitosamente", createdUser));
        } catch (RuntimeException ex) {
            logger.error("Error al crear el usuario: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseUtils.errorResponse(ex.getMessage()));
        }
    }

    @GetMapping("/id/{idUser}")
    public ResponseEntity<?> getUserById(@PathVariable UUID idUser) {
        try {
            logger.info("Buscando usuario con ID: {}", idUser);
            User user = userService.findUserById(idUser);
            logger.info("Usuario encontrado: {}", user.getName());
            return ResponseEntity.ok(ResponseUtils.successResponse("Usuario encontrado", user));
        } catch (RuntimeException ex) {
            logger.error("Usuario no encontrado con ID: {} - {}", idUser, ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtils.errorResponse(ex.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            logger.info("Obteniendo lista de usuarios, página: {}, tamaño: {}", pageable.getPageNumber(), pageable.getPageSize());
            Page<User> users = userService.findAllUsers(pageable);
            logger.info("Usuarios obtenidos: {}", users.getTotalElements());
            return ResponseEntity.ok(ResponseUtils.successResponse("Usuarios obtenidos", users));
        } catch (Exception ex) {
            logger.error("Error al obtener usuarios: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtils.errorResponse("Error al obtener usuarios"));
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> searchByFullName(@PathVariable String name, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            logger.info("Buscando usuarios por nombre: {}", name);
            Page<User> users = userService.searchUsersByName(name, pageable);
            logger.info("Resultados de búsqueda obtenidos para nombre: {}", users.getTotalElements());
            return ResponseEntity.ok(ResponseUtils.successResponse("Búsqueda exitosa", users));
        } catch (Exception ex) {
            logger.error("Error al buscar usuarios por nombre: {} - {}", name, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtils.errorResponse("Error al buscar usuarios"));
        }
    }

    @GetMapping("/role/{idRole}")
    public ResponseEntity<?> getUsersByRole(@PathVariable Long idRole, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            logger.info("Buscando usuarios por rol ID: {}", idRole);
            Page<User> users = userService.findUsersByRole(idRole, pageable);
            logger.info("Usuarios encontrados para rol ID: {}", idRole);
            return ResponseEntity.ok(ResponseUtils.successResponse("Usuarios por rol obtenidos", users));
        } catch (Exception ex) {
            logger.error("Error al buscar usuarios por rol ID: {} - {}", idRole, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtils.errorResponse("Error al buscar usuarios por rol"));
        }
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<?> searchByDni(@PathVariable String dni, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            logger.info("Buscando usuarios por DNI: {}", dni);
            Page<User> users = userService.searchUsersByDni(dni, pageable);
            logger.info("Usuarios encontrados por DNI: {}", users.getTotalElements());
            return ResponseEntity.ok(ResponseUtils.successResponse("Usuarios por dni obtenidos", users));
        } catch (Exception ex) {
            logger.error("Error al buscar usuarios por DNI: {} - {}", dni, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtils.errorResponse("Error al buscar usuarios"));
        }
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<?> getUsersByStatus(@PathVariable boolean status, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        try {
            logger.info("Buscando usuarios por estado: {}", status);
            Page<User> users = userService.findUsersByStatus(status, pageable);
            logger.info("Usuarios encontrados con estado: {}", status);
            return ResponseEntity.ok(ResponseUtils.successResponse("Usuarios por estado obtenidos", users));
        } catch (Exception ex) {
            logger.error("Error al buscar usuarios por estado: {} - {}", status, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtils.errorResponse("Error al buscar usuarios por estado"));
        }
    }

    @GetMapping("/total")
    public Long getTotalUsers() {
        long totalUsers = userService.getTotalUsers();
        logger.info("Total de usuarios: {}", totalUsers);
        return totalUsers;
    }

    @PutMapping("/update/{idUser}")
    public ResponseEntity<?> updateUser(@PathVariable UUID idUser, @RequestBody User user) {
        try {
            logger.info("Actualizando usuario con ID: {}", idUser);
            User updatedUser = userService.updateUser(idUser, user);
            logger.info("Usuario actualizado con ID: {}", updatedUser.getIdUser());
            return ResponseEntity.ok(ResponseUtils.successResponse("Usuario actualizado", updatedUser));
        } catch (RuntimeException ex) {
            logger.error("Error al actualizar el usuario con ID: {} - {}", idUser, ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseUtils.errorResponse(ex.getMessage()));
        }
    }

    @DeleteMapping("/delete/{idUser}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID idUser) {
        try {
            logger.info("Eliminando usuario con ID: {}", idUser);
            userService.deleteUser(idUser);
            logger.info("Usuario eliminado con ID: {}", idUser);
            return ResponseEntity.ok(ResponseUtils.successResponse("Usuario eliminado", null));
        } catch (RuntimeException ex) {
            logger.error("Error al eliminar el usuario con ID: {} - {}", idUser, ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseUtils.errorResponse(ex.getMessage()));
        }
    }
}
