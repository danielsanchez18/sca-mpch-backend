package com.mpch.controlDeAsistencias_backend.servicesImpl;

import com.mpch.controlDeAsistencias_backend.model.Assistance;
import com.mpch.controlDeAsistencias_backend.model.Intern;
import com.mpch.controlDeAsistencias_backend.repository.AssistanceRepository;
import com.mpch.controlDeAsistencias_backend.repository.InternRepository;
import com.mpch.controlDeAsistencias_backend.services.AssistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AssistanceServiceImpl implements AssistanceService {

    @Autowired
    private AssistanceRepository assistanceRepository;

    @Autowired
    private InternRepository internRepository;

    @Override
    public Assistance registerCheckIn(String dni) {
//        Intern intern = internRepository.findByDni(dni)
//                .orElseThrow(() -> new RuntimeException("Practicante no encontrado"));
//
//        if (assistanceRepository.existsByInternAndCheckIn(intern, LocalDate.now())) {
//            throw new RuntimeException("Ya se ha registrado la entrada del practicante");
//        }
//
//        Assistance assistance = new Assistance();
//        assistance.setIntern(intern);
//        assistance.setCheckIn(LocalDate.now());
//        assistance.setHoursWorked(0);
//
//        return assistanceRepository.save(assistance);
        return null;
    }

    @Override
    public Assistance registerCheckOut(String dni) {
//        Intern intern = internRepository.findByDni(dni)
//                .orElseThrow(() -> new RuntimeException("Practicante no encontrado con DNI: " + dni));
//
//        Assistance assistance = assistanceRepository.findTodayAssistance(intern, LocalDate.now())
//                .orElseThrow(() -> new RuntimeException("No se ha registrado una entrada hoy"));
//
//        if (assistance.getCheckOut() != null) {
//            throw new RuntimeException("La salida ya ha sido registrada hoy");
//        }
//
//        assistance.setCheckOut(LocalDate.now());
//        assistance.setHoursWorked(calculateHoursWorked(assistance.getCheckIn(), assistance.getCheckOut()));
//        return assistanceRepository.save(assistance);
        return null;
    }

    @Override
    public List<Assistance> getAssistancesByDate(LocalDate date, int page, int size) {
//        return assistanceRepository.findAllByDate(date, page, size);
        return null;
    }

    @Override
    public List<Assistance> searchAssistancesByInternName(String name, int page, int size) {
//        return assistanceRepository.searchByInternName(name, page, size);
        return null;
    }

    @Override
    public double getHoursWorkedByInternOnDate(String dni, LocalDate date) {
//        return assistanceRepository.getHoursWorkedByInternOnDate(dni, date);
        return 0;
    }

    @Override
    public int getAttendanceCountByAreaOnDate(String areaName, LocalDate date) {
//        return assistanceRepository.getAttendanceCountByAreaOnDate(areaName, date);
        return 0;
    }

    @Override
    public double getMonthlyHoursWorkedByArea(String areaName, int month, int year) {
//        return assistanceRepository.getMonthlyHoursByArea(areaName, month, year);
        return 0;
    }

    @Override
    public double getMonthlyHoursWorkedByUniversity(String universityName, int month, int year) {
//        return assistanceRepository.getMonthlyHoursByUniversity(universityName, month, year);
        return 0;
    }

    private double calculateHoursWorked(LocalDate checkIn, LocalDate checkOut) {
//        LocalDateTime checkInDateTime = checkIn.atStartOfDay();
//        LocalDateTime checkOutDateTime = checkOut.atStartOfDay();

        return Duration.between(checkIn, checkOut).toHours();
    }
}
