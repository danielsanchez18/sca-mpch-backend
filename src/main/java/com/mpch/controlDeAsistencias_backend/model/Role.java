package com.mpch.controlDeAsistencias_backend.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_role")
    private UUID idRole;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    public Role() { }

    public Role(UUID idRole, String name) {
        this.idRole = idRole;
        this.name = name;
    }

    public UUID getIdRole() {
        return idRole;
    }

    public void setIdRole(UUID idRole) {
        this.idRole = idRole;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
