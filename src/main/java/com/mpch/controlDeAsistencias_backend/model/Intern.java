package com.mpch.controlDeAsistencias_backend.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "intern")
public class Intern {

    @Id
    @Column(name = "id_intern", nullable = false, unique = true, length = 11)
    private String idIntern;

    @OneToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_area", referencedColumnName = "id_area", nullable = false)
    private Area area;

    @ManyToOne
    @JoinColumn(name = "id_university", referencedColumnName = "id_university", nullable = false)
    private University university;

    @Column(name = "total_hours", nullable = false)
    private Long totalHours;

    public Intern() { }

    public Intern(String idIntern, User user, Area area, University university, Long totalHours) {
        this.idIntern = idIntern;
        this.user = user;
        this.area = area;
        this.university = university;
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

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public Long getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(Long totalHours) {
        this.totalHours = totalHours;
    }
}
