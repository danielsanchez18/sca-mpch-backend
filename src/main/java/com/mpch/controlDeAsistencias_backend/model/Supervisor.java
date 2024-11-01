package com.mpch.controlDeAsistencias_backend.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "supervisor")
public class Supervisor {

    @Id
    @Column(name = "id_supervisor", nullable = false, unique = true, length = 11)
    private String idSupervisor;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_area", referencedColumnName = "id_area", nullable = false)
    private Area area;

    public Supervisor() { }

    public Supervisor(String idSupervisor, String password, User user, Area area) {
        this.idSupervisor = idSupervisor;
        this.password = password;
        this.user = user;
        this.area = area;
    }

    public String getIdSupervisor() {
        return idSupervisor;
    }

    public void setIdSupervisor(String idSupervisor) {
        this.idSupervisor = idSupervisor;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
