package com.mpch.controlDeAsistencias_backend.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "certificated")
public class Certificated {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_certificated")
    private UUID idCertificated;

    @OneToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", nullable = false)
    private User user;

    @Column(name = "status", nullable = false)
    private boolean status;

    public Certificated() { }

    public Certificated(UUID idCertificated, User user, boolean status) {
        this.idCertificated = idCertificated;
        this.user = user;
        this.status = status;
    }

    public UUID getIdCertificated() {
        return idCertificated;
    }

    public void setIdCertificated(UUID idCertificated) {
        this.idCertificated = idCertificated;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
