package com.mpch.controlDeAsistencias_backend.controller;

import com.mpch.controlDeAsistencias_backend.model.User;
import com.mpch.controlDeAsistencias_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public User saveUser(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping("/id/{idUser}")
    public User findUserById(@PathVariable UUID idUser) {
        return userService.findUserById(idUser);
    }

    @GetMapping("/all")
    public Page<User> findAllUsers(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        return userService.findAllUsers(pageable);
    }

    @GetMapping("/role/{role}")
    public List<User> findUsersByRole(@PathVariable String role, @PageableDefault(page = 1, size = 10) Pageable pageable) {
        return userService.findUsersByRole(role, pageable.getPageNumber(), pageable.getPageSize());
    }

    @GetMapping("/name/{name}")
    public List<User> searchUsersByName(@PathVariable String name, @PageableDefault(page = 1, size = 10) Pageable pageable) {
        return userService.searchUsersByName(name, pageable.getPageNumber(), pageable.getPageSize());
    }

    @GetMapping("/dni/{dni}")
    public List<User> searchUsersByDni(@PathVariable String dni, @PageableDefault(page = 1, size = 10) Pageable pageable) {
        return userService.searchUsersByDni(dni, pageable.getPageNumber(), pageable.getPageSize());
    }

    @GetMapping("/enabled")
    public List<User> findUsersEnabled(@PageableDefault(page = 1, size = 10) Pageable pageable) {
        return userService.findUsersEnabled(pageable.getPageNumber(), pageable.getPageSize());
    }

    @GetMapping("/disabled")
    public List<User> findUsersDisabled(@PageableDefault(page = 1, size = 10) Pageable pageable) {
        return userService.findUsersDisabled(pageable.getPageNumber(), pageable.getPageSize());
    }

    @GetMapping("/total")
    public Long getTotalUsers() {
        return userService.getTotalUsers();
    }

    @PutMapping("/update/{idUser}")
    public User updateUser(@PathVariable UUID idUser, @RequestBody User user) {
        return userService.updateUser(idUser, user);
    }

    @DeleteMapping("/delete/{idUser}")
    public void deleteUser(@PathVariable UUID idUser) {
        userService.deleteUser(idUser);
    }
}
