package com.mpch.controlDeAsistencias_backend.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "area")
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_area")
    private UUID idArea;

    @Column(name = "name", unique = true, nullable = false, length = 25)
    private String name;

    @Column(name = "nro_vacancies", nullable = false)
    private Long nro_vacancies;

    @Column(name = "hours_certified", nullable = false)
    private Long hours_certified;

    @Column(name = "status", nullable = false)
    private boolean status;

    public Area() { }

    public Area(UUID idArea, String name, Long nro_vacancies, Long hours_certified, boolean status) {
        this.idArea = idArea;
        this.name = name;
        this.nro_vacancies = nro_vacancies;
        this.hours_certified = hours_certified;
        this.status = status;
    }

    public UUID getIdArea() {
        return idArea;
    }

    public void setIdArea(UUID idArea) {
        this.idArea = idArea;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getNro_vacancies() {
        return nro_vacancies;
    }

    public void setNro_vacancies(Long nro_vacancies) {
        this.nro_vacancies = nro_vacancies;
    }

    public Long getHours_certified() {
        return hours_certified;
    }

    public void setHours_certified(Long hours_certified) {
        this.hours_certified = hours_certified;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
