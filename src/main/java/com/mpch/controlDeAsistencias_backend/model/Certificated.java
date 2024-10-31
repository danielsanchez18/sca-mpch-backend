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
    @JoinColumn(name = "id_intern", referencedColumnName = "id_intern", nullable = false)
    private Intern intern;

    @Column(name = "status", nullable = false)
    private boolean status;

    public Certificated() { }

    public Certificated(UUID idCertificated, Intern intern, boolean status) {
        this.idCertificated = idCertificated;
        this.intern = intern;
        this.status = status;
    }

    public UUID getIdCertificated() {
        return idCertificated;
    }

    public void setIdCertificated(UUID idCertificated) {
        this.idCertificated = idCertificated;
    }

    public Intern getIntern() {
        return intern;
    }

    public void setIntern(Intern intern) {
        this.intern = intern;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
