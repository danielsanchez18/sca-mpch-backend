package com.mpch.controlDeAsistencias_backend.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
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
    
    @Column(name = "generated_date", nullable = false)
    private LocalDateTime generatedDate;

    public Certificated() { }

    public Certificated(UUID idCertificated, Intern intern, boolean status, LocalDateTime generatedDate) {
        this.idCertificated = idCertificated;
        this.intern = intern;
        this.status = status;
        this.generatedDate = generatedDate;
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

    public LocalDateTime getGeneratedDate() {
        return generatedDate;
    }

    public void setGeneratedDate(LocalDateTime generatedDate) {
        this.generatedDate = generatedDate;
    }

}
