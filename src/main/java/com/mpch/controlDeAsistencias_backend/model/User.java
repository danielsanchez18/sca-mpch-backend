package com.mpch.controlDeAsistencias_backend.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_user")
    private UUID idUser;

    @ManyToOne
    @JoinColumn(name = "id_role", referencedColumnName = "id_role", nullable = false)
    private Role role;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "lastname", nullable = false, length = 30)
    private String lastname;

    @Column(name = "dni", nullable = false,  unique = true, length = 8)
    private String dni;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "birthDate", nullable = false)
    private Date birthdate;

    @Column(name = "photo")
    private String photo;

    @Column(name = "status", nullable = false)
    private boolean status;

    public User() { }

    public User(UUID idUser, Role role, String name, String lastname, String dni, LocalDateTime createdAt, LocalDateTime updatedAt, Date birthdate, String photo, boolean status) {
        this.idUser = idUser;
        this.role = role;
        this.name = name;
        this.lastname = lastname;
        this.dni = dni;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.birthdate = birthdate;
        this.photo = photo;
        this.status = status;
    }

    public UUID getIdUser() {
        return idUser;
    }

    public void setIdUser(UUID idUser) {
        this.idUser = idUser;
    }

    public Role getRole() {
        return role;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
