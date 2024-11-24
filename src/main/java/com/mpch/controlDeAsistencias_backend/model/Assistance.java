package com.mpch.controlDeAsistencias_backend.model;

import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "assistance")
public class Assistance {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_assistance", nullable = false)
    private UUID idAssistance;

    @ManyToOne
    @JoinColumn(name = "id_intern", referencedColumnName = "id_intern", nullable = false)
    private Intern intern;

    // @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "check_in")
    private LocalDateTime checkIn;

    // @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "check_out")
    private LocalDateTime checkOut;

    @Column(name = "hours_worked", nullable = false)
    private double hoursWorked;

    @PrePersist
    @PreUpdate
    private void calculateHoursWorked() {
        if (checkIn != null && checkOut != null) {
            long seconds = Duration.between(checkIn, checkOut).getSeconds();
            this.hoursWorked = Math.min(10.0, seconds / 3600.0); // Máximo 10 horas
        } else {
            this.hoursWorked = 0.0; // Si no hay Check-Out, no hay horas
        }
    }

    public Assistance() { }

    public Assistance(UUID idAssistance, Intern intern, LocalDateTime checkIn, LocalDateTime checkOut, double hoursWorked) {
        this.idAssistance = idAssistance;
        this.intern = intern;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.hoursWorked = hoursWorked;
    }

    public UUID getIdAssistance() {
        return idAssistance;
    }

    public void setIdAssistance(UUID idAssistance) {
        this.idAssistance = idAssistance;
    }

    public Intern getIntern() {
        return intern;
    }

    public void setIntern(Intern intern) {
        this.intern = intern;
    }

    public LocalDateTime getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDateTime checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDateTime getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDateTime checkOut) {
        this.checkOut = checkOut;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }
}
