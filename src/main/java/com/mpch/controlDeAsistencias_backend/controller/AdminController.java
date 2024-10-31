package com.mpch.controlDeAsistencias_backend.controller;

import com.mpch.controlDeAsistencias_backend.model.Admin;
import com.mpch.controlDeAsistencias_backend.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/save")
    public Admin saveAdmin(@RequestBody Admin admin){
        return adminService.saveAdmin(admin);
    }

    @GetMapping("/id/{idAdmin}")
    public Admin getAdminById(@PathVariable String idAdmin){
        return adminService.getAdminById(idAdmin);
    }

    @GetMapping("/all")
    public Page<Admin> getAllAdmins(@PageableDefault(page = 0, size = 10) Pageable pageable){
        return adminService.getAllAdmins(pageable);
    }

    @GetMapping("/name/{name}")
    public List<Admin> searchAdminsByName(@PathVariable String name, @PageableDefault(page = 1, size = 10) Pageable pageable){
        return adminService.searchAdminsByName(name, pageable.getPageNumber(), pageable.getPageSize());
    }

    @GetMapping("/total")
    public Long getTotalAdmins(){
        return adminService.getTotalAdmins();
    }

    @PutMapping("/update/{idAdmin}")
    public Admin updateAdmin(@PathVariable String idAdmin, @RequestBody Admin admin){
        return adminService.updateAdmin(idAdmin, admin);
    }

    @DeleteMapping("/delete/{idUser}")
    public void deleteAdmin(@PathVariable UUID idUser){
        adminService.deleteAdmin(idUser);
    }

}
