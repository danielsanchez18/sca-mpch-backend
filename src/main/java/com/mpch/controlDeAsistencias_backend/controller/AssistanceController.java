package com.mpch.controlDeAsistencias_backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mpch.controlDeAsistencias_backend.model.Assistance;
import com.mpch.controlDeAsistencias_backend.services.AssistanceService;
import com.mpch.controlDeAsistencias_backend.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/assistance")
@CrossOrigin(origins = "http://localhost:4200")
public class AssistanceController {

    @Autowired
    private AssistanceService assistanceService;

    private static final Logger logger = LoggerFactory.getLogger(AssistanceController.class);

    @PostMapping("/check-in")
    public ResponseEntity<?> registerCheckIn(@RequestParam String dni) {
        logger.info("Registrando Check-In para el DNI: {}", dni);
        try {
            Assistance assistance = assistanceService.registerCheckIn(dni);
            logger.info("Check-In registrado con éxito para el DNI: {}", dni);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ResponseUtils.successResponse("Check-In registrado con éxito", assistance));
        } catch (RuntimeException ex) {
            logger.error("Error al registrar Check-In para el DNI: {}: {}", dni, ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseUtils.errorResponse(ex.getMessage()));
        }
    }

    @PostMapping("/check-out")
    public ResponseEntity<?> registerCheckOut(@RequestParam String dni) {
        logger.info("Registrando Check-Out para el DNI: {}", dni);
        try {
            Assistance assistance = assistanceService.registerCheckOut(dni);
            logger.info("Check-Out registrado con éxito para el DNI: {}", dni);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ResponseUtils.successResponse("Check-Out registrado con éxito", assistance));
        } catch (RuntimeException ex) {
            logger.error("Error al registrar Check-Out para el DNI: {}: {}", dni, ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseUtils.errorResponse(ex.getMessage()));
        }
    }

    @GetMapping("/date")
    public ResponseEntity<?> getAssistancesByDate(
            @RequestParam LocalDate date,
            @PageableDefault(size = 10, page = 0) Pageable pageable
    ) {
        logger.info("Obteniendo asistencias para la fecha: {}", date);
        try {
            Page<Assistance> assistances = assistanceService.getAssistancesByDate(date, pageable);
            logger.info("Asistencias obtenidas con éxito para la fecha: {}", date);
            return ResponseEntity.ok(ResponseUtils.successResponse("Asistencias obtenidas con éxito", assistances));
        } catch (Exception ex) {
            logger.error("Error al obtener asistencias para la fecha: {}: {}", date, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtils.errorResponse("Error al obtener asistencias por fecha"));
        }
    }

    @GetMapping("/date-range")
    public ResponseEntity<?> getAssistancesByDateRange(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate,
            @PageableDefault(size = 10, page = 0) Pageable pageable
    ) {
        logger.info("Obteniendo asistencias entre las fechas: {} y {}", startDate, endDate);
        try {
            Page<Assistance> assistances = assistanceService.getAssistancesByDateRange(startDate, endDate, pageable);
            logger.info("Asistencias obtenidas con éxito entre las fechas: {} y {}", startDate, endDate);
            return ResponseEntity.ok(ResponseUtils.successResponse("Asistencias obtenidas con éxito", assistances));
        } catch (Exception ex) {
            logger.error("Error al obtener asistencias entre las fechas: {} y {}: {}", startDate, endDate, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtils.errorResponse("Error al obtener asistencias por rango de fechas"));
        }
    }

    @GetMapping("/intern-name")
    public ResponseEntity<?> searchAssistancesByInternName(
            @RequestParam String name,
            @PageableDefault(size = 10, page = 0) Pageable pageable
    ) {
        logger.info("Buscando asistencias por nombre del practicante: {}", name);
        try {
            Page<Assistance> assistances = assistanceService.searchAssistancesByInternName(name, pageable);
            logger.info("Asistencias obtenidas con éxito para el practicante: {}", name);
            return ResponseEntity.ok(ResponseUtils.successResponse("Asistencias obtenidas con éxito", assistances));
        } catch (Exception ex) {
            logger.error("Error al buscar asistencias por nombre del practicante: {}: {}", name, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtils.errorResponse("Error al buscar asistencias por nombre del practicante"));
        }
    }

    @GetMapping("/area")
    public ResponseEntity<?> findAssistancesByArea(
            @RequestParam String areaName,
            @PageableDefault(size = 10, page = 0) Pageable pageable
    ) {
        logger.info("Buscando asistencias por área: {}", areaName);
        try {
            Page<Assistance> assistances = assistanceService.findAssistancesByArea(areaName, pageable);
            logger.info("Asistencias obtenidas con éxito para el área: {}", areaName);
            return ResponseEntity.ok(ResponseUtils.successResponse("Asistencias obtenidas por área con éxito", assistances));
        } catch (Exception ex) {
            logger.error("Error al buscar asistencias por área: {}: {}", areaName, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtils.errorResponse("Error al buscar asistencias por área"));
        }
    }

    @GetMapping("/hours-by-date")
    public ResponseEntity<?> getHoursWorkedByInternOnDate(
            @RequestParam String dni,
            @RequestParam LocalDate date
    ) {
        logger.info("Obteniendo horas trabajadas por el practicante con DNI: {} para la fecha: {}", dni, date);
        try {
            double hours = assistanceService.getHoursWorkedByInternOnDate(dni, date);
            logger.info("Horas trabajadas obtenidas con éxito para el DNI: {} en la fecha: {}", dni, date);
            return ResponseEntity.ok(ResponseUtils.successResponse("Horas trabajadas obtenidas con éxito", hours));
        } catch (RuntimeException ex) {
            logger.error("Error al obtener horas trabajadas para el DNI: {} en la fecha: {}: {}", dni, date, ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtils.errorResponse(ex.getMessage()));
        }
    }

    @GetMapping("/hours-by-area")
    public ResponseEntity<?> getMonthlyHoursWorkedByArea(@RequestParam String areaName,
                                                         @RequestParam int month,
                                                         @RequestParam int year) {
        logger.info("Obteniendo horas trabajadas mensuales para el área: {} en el mes: {} y año: {}", areaName, month, year);
        try {
            double hours = assistanceService.getMonthlyHoursWorkedByArea(areaName, month, year);
            logger.info("Horas trabajadas obtenidas con éxito para el área: {} en el mes: {} y año: {}", areaName, month, year);
            return ResponseEntity.ok(ResponseUtils.successResponse("Horas mensuales trabajadas por área obtenidas con éxito", hours));
        } catch (Exception ex) {
            logger.error("Error al obtener horas trabajadas para el área: {} en el mes: {} y año: {}: {}", areaName, month, year, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtils.errorResponse("Error al obtener horas trabajadas por área"));
        }
    }

    @GetMapping("/hours-by-university")
    public ResponseEntity<?> getMonthlyHoursWorkedByUniversity(@RequestParam String universityName,
                                                               @RequestParam int month,
                                                               @RequestParam int year) {
        logger.info("Obteniendo horas trabajadas mensuales para la universidad: {} en el mes: {} y año: {}", universityName, month, year);
        try {
            double hours = assistanceService.getMonthlyHoursWorkedByUniversity(universityName, month, year);
            logger.info("Horas trabajadas obtenidas con éxito para la universidad: {} en el mes: {} y año: {}", universityName, month, year);
            return ResponseEntity.ok(ResponseUtils.successResponse("Horas mensuales trabajadas por universidad obtenidas con éxito", hours));
        } catch (Exception ex) {
            logger.error("Error al obtener horas trabajadas para la universidad: {} en el mes: {} y año: {}: {}", universityName, month, year, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtils.errorResponse("Error al obtener horas trabajadas por universidad"));
        }
    }

}

