package com.mpch.controlDeAsistencias_backend.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "assistance")
public class Assistance {

    @Id
    @Column(name = "id_assistance", nullable = false, unique = true, length = 11)
    private String idAssistance;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", nullable = false)
    private User user;

    @Column(name = "check_in")
    private LocalDate checkIn;

    @Column(name = "check_out")
    private LocalDate checkOut;

    @Column(name = "hours_worked", nullable = false)
    private double hoursWorked;

    public Assistance() { }

    public Assistance(String idAssistance, User user, LocalDate checkIn, LocalDate checkOut, double hoursWorked) {
        this.idAssistance = idAssistance;
        this.user = user;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.hoursWorked = hoursWorked;
    }

    public String getIdAssistance() {
        return idAssistance;
    }

    public void setIdAssistance(String idAssistance) {
        this.idAssistance = idAssistance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
