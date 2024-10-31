package com.mpch.controlDeAsistencias_backend.model;

import jakarta.persistence.*;

import java.time.LocalDate;
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

    @Column(name = "check_in")
    private LocalDate checkIn;

    @Column(name = "check_out")
    private LocalDate checkOut;

    @Column(name = "hours_worked", nullable = false)
    private double hoursWorked;

    public Assistance() { }

    public Assistance(UUID idAssistance, Intern intern, LocalDate checkIn, LocalDate checkOut, double hoursWorked) {
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

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }
}
