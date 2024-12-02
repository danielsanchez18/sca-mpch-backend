package com.mpch.controlDeAsistencias_backend.servicesImpl;

import com.mpch.controlDeAsistencias_backend.model.Assistance;
import com.mpch.controlDeAsistencias_backend.model.Intern;
import com.mpch.controlDeAsistencias_backend.repository.AssistanceRepository;
import com.mpch.controlDeAsistencias_backend.repository.InternRepository;
import com.mpch.controlDeAsistencias_backend.services.AssistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class AssistanceServiceImpl implements AssistanceService {

    @Autowired
    private AssistanceRepository assistanceRepository;

    @Autowired
    private InternRepository internRepository;

    private void updateTotalHours(Intern intern) {
        // Sumar todas las horas trabajadas desde las asistencias
        double totalHours = assistanceRepository.getTotalHoursWorkedByIntern(intern.getIdIntern());
        intern.setTotalHours(totalHours);
        internRepository.save(intern);
    }

    @Override
    public Assistance registerCheckIn(String dni) {

        Intern intern = internRepository.findByUser_Dni(dni).
                orElseThrow(() -> new RuntimeException("No se encontró el practicante con DNI: " + dni)
        );

        // Validar que no haya asistencias activas (sin Check-Out)
        if (assistanceRepository.findActiveAssistanceByIntern(intern.getIdIntern()).isPresent()) {
            throw new RuntimeException("El practicante ya tiene una asistencia activa.");
        }

        // Crear nueva asistencia
        Assistance assistance = new Assistance();
        assistance.setIntern(intern);
        assistance.setCheckIn(LocalDateTime.now());

        return assistanceRepository.save(assistance);
    }

    @Override
    public Assistance registerCheckOut(String dni) {

        Intern intern = internRepository.findByUser_Dni(dni).orElseThrow(
                () -> new RuntimeException("No se encontró el practicante con DNI: " + dni)
        );

        // Obtener la asistencia activa
        Assistance activeAssistance = assistanceRepository.findActiveAssistanceByIntern(intern.getIdIntern()).orElseThrow(
                () -> new RuntimeException("No se encontró una asistencia activa para el practicante.")
        );

        // Registrar Check-Out y guardar
        activeAssistance.setCheckOut(LocalDateTime.now());
        assistanceRepository.save(activeAssistance);

        // Actualizar horas trabajadas totales del practicante
        updateTotalHours(intern);

        return assistanceRepository.save(activeAssistance);
    }

    @Override
    public Page<Assistance> getAssistancesByDate(LocalDate date, Pageable pageable) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);
        return assistanceRepository.findAllByCheckInBetween(startOfDay, endOfDay, pageable);
    }

    @Override
    public Page<Assistance> getAssistancesByDateRange(LocalDate startDate, LocalDate endDate, Pageable pageable) {
        LocalDateTime startOfRange = startDate.atStartOfDay();
        LocalDateTime endOfRange = endDate.atTime(LocalTime.MAX);
        return assistanceRepository.findAllByCheckInBetween(startOfRange, endOfRange, pageable);
    }

    @Override
    public Page<Assistance> searchAssistancesByInternName(String name, Pageable pageable) {
        return assistanceRepository.findByInternFullName(name, pageable);
    }

    @Override
    public Page<Assistance> findAssistancesByArea(String areaName, Pageable pageable) {
        return assistanceRepository.findByIntern_AreaUniversity_Area_Name(areaName, pageable);
    }

    @Override
    public double getHoursWorkedByInternOnDate(String dni, LocalDate date) {
        Intern intern = internRepository.findByUser_Dni(dni).orElseThrow(
                () -> new RuntimeException("No se encontró el practicante con DNI: " + dni)
        );

        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);

        return assistanceRepository.sumHoursWorkedByInternAndDate(intern.getIdIntern(), startOfDay, endOfDay);
    }

    @Override
    public double getMonthlyHoursWorkedByArea(String areaName, int month, int year) {
        return assistanceRepository.sumHoursWorkedByAreaAndMonth(areaName, month, year);
    }

    @Override
    public double getMonthlyHoursWorkedByUniversity(String universityName, int month, int year) {
        return assistanceRepository.sumHoursWorkedByUniversityAndMonth(universityName, month, year);
    }

    @Override
    public boolean hasOverlap(String idIntern, LocalDateTime checkIn, LocalDateTime checkOut) {
        return assistanceRepository.hasOverlap(idIntern, checkIn, checkOut);
    }

}
