package com.mpch.controlDeAsistencias_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "area")
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_area")
    private Long idArea;

    @Column(name = "name", unique = true, nullable = false, length = 25)
    private String name;

    @Column(name = "nro_vacancies", nullable = false)
    private Long nroVacancies;

    @Column(name = "status", nullable = false)
    private boolean status;

    public Area() { }

    public Area(Long idArea, String name, Long nroVacancies, boolean status) {
        this.idArea = idArea;
        this.name = name;
        this.nroVacancies = nroVacancies;
        this.status = status;
    }

    public Long getIdArea() {
        return idArea;
    }

    public void setIdArea(Long idArea) {
        this.idArea = idArea;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getNroVacancies() {
        return nroVacancies;
    }

    public void setNroVacancies(Long nroVacancies) {
        this.nroVacancies = nroVacancies;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
