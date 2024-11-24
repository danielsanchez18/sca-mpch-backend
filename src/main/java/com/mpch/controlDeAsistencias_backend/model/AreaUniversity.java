package com.mpch.controlDeAsistencias_backend.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "area_university")
public class AreaUniversity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_area_university")
    private UUID idAreaUniversity;

    @ManyToOne
    @JoinColumn(name = "id_area", referencedColumnName = "id_area", nullable = false)
    private Area area;

    @ManyToOne
    @JoinColumn(name = "id_university", referencedColumnName = "id_university", nullable = false)
    private University university;

    @Column(name = "hours_certified", nullable = false)
    private int hoursCertified;

    public AreaUniversity() { }

    public AreaUniversity(UUID idAreaUniversity, Area area, University university, int hoursCertified) {
        this.idAreaUniversity = idAreaUniversity;
        this.area = area;
        this.university = university;
        this.hoursCertified = hoursCertified;
    }

    public UUID getIdAreaUniversity() {
        return idAreaUniversity;
    }

    public void setIdAreaUniversity(UUID idAreaUniversity) {
        this.idAreaUniversity = idAreaUniversity;
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

    public int getHoursCertified() {
        return hoursCertified;
    }

    public void setHoursCertified(int hoursCertified) {
        this.hoursCertified = hoursCertified;
    }

}
