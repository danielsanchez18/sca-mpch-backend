package com.mpch.controlDeAsistencias_backend.controller;

import com.mpch.controlDeAsistencias_backend.model.Role;
import com.mpch.controlDeAsistencias_backend.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/role")
@CrossOrigin("*")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/save")
    public void addRole(@RequestBody Role role) {
        roleService.saveRole(role);
    }

    @GetMapping("/id/{idRole}")
    public Role getRoleById(@PathVariable UUID idRole) {
        return roleService.findRoleById(idRole);
    }

    @GetMapping("/all")
    public Page<Role> getAllRoles(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        return roleService.getAllRoles(pageable);
    }

    @GetMapping("/search/{name}")
    public List<Role> searchRoleByName(@PathVariable String name, @PageableDefault(page = 1, size = 10) Pageable pageable) {
        return roleService.searchRoleByName(name, pageable.getPageNumber(), pageable.getPageSize());
    }

    @GetMapping("/total")
    public Long getTotalRoles() {
        return roleService.getTotalRoles();
    }

    @PutMapping("/update/{idRole}")
    public Role updateRole(@PathVariable UUID idRole, @RequestBody Role role) {
        return roleService.updateRole(idRole, role);
    }

    @DeleteMapping("/delete/{idRole}")
    public void deleteRole(@PathVariable UUID idRole) {
        roleService.deleteRole(idRole);
    }

}
