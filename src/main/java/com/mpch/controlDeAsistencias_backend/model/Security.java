package com.mpch.controlDeAsistencias_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "security")
public class Security {

    @Id
    @Column(name = "id_security", nullable = false, unique = true, length = 11)
    private String idSecurity;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", nullable = false)
    private User user;

    @PrePersist
    private void generateId() {
        if (idSecurity == null || idSecurity.isEmpty()) {
            long count = System.currentTimeMillis() % 1000000; // Generar un número único basado en el tiempo
            this.idSecurity = "PS24" + String.format("%06d", count);
        }
    }

    public Security() { }

    public Security(String idSecurity, String password, User user) {
        this.idSecurity = idSecurity;
        this.password = password;
        this.user = user;
    }

    public String getIdSecurity() {
        return idSecurity;
    }

    public void setIdSecurity(String idSecurity) {
        this.idSecurity = idSecurity;
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
}
