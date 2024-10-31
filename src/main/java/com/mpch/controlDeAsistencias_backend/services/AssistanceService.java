package com.mpch.controlDeAsistencias_backend.services;

import com.mpch.controlDeAsistencias_backend.model.Assistance;

import java.time.LocalDate;
import java.util.List;

public interface AssistanceService {

    Assistance registerCheckIn(String dni);

    Assistance registerCheckOut(String dni);

    List<Assistance> getAssistancesByDate(LocalDate date, int page, int size);

    List<Assistance> searchAssistancesByInternName(String name, int page, int size);

    double getHoursWorkedByInternOnDate(String dni, LocalDate date);

    int getAttendanceCountByAreaOnDate(String areaName, LocalDate date);

    double getMonthlyHoursWorkedByArea(String areaName, int month, int year);

    double getMonthlyHoursWorkedByUniversity(String universityName, int month, int year);

}
