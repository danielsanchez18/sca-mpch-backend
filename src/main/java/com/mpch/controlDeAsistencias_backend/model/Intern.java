package com.mpch.controlDeAsistencias_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "intern")
public class Intern {

    @Id
    @Column(name = "id_intern", nullable = false, unique = true, length = 11)
    private String idIntern;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_area_university", referencedColumnName = "id_area_university", nullable = false)
    private AreaUniversity areaUniversity;

    @Column(name = "total_hours", nullable = false)
    private double totalHours;

    @PrePersist
    private void generateId() {
        if (idIntern == null || idIntern.isEmpty()) {
            long count = System.currentTimeMillis() % 1000000; // Generar un número único basado en el tiempo
            this.idIntern = "I24" + String.format("%07d", count);
        }
    }

    public Intern() { }

    public Intern(String idIntern, User user, AreaUniversity areaUniversity, double totalHours) {
        this.idIntern = idIntern;
        this.user = user;
        this.areaUniversity = areaUniversity;
        this.totalHours = totalHours;
    }

    public String getIdIntern() {
        return idIntern;
    }

    public void setIdIntern(String idIntern) {
        this.idIntern = idIntern;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AreaUniversity getAreaUniversity() {
        return areaUniversity;
    }

    public void setAreaUniversity(AreaUniversity areaUniversity) {
        this.areaUniversity = areaUniversity;
    }

    public double getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(double totalHours) {
        this.totalHours = totalHours;
    }

}
