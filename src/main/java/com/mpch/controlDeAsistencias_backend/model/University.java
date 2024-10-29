package com.mpch.controlDeAsistencias_backend.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "university")
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_university")
    private UUID idUniversity;

    @Column(name = "name", unique = true, nullable = false, length = 100)
    private String name;

    @Column(name = "acronym", nullable = false, length = 10)
    private String acronym;

    @Column(name = "photo")
    private String photo;

    @Column(name = "status", nullable = false)
    private boolean status;

    public University() { }

    public University(UUID idUniversity, String name, String acronym, String photo, boolean status) {
        this.idUniversity = idUniversity;
        this.name = name;
        this.acronym = acronym;
        this.photo = photo;
        this.status = status;
    }

    public UUID getIdUniversity() {
        return idUniversity;
    }

    public void setIdUniversity(UUID idUniversity) {
        this.idUniversity = idUniversity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
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
