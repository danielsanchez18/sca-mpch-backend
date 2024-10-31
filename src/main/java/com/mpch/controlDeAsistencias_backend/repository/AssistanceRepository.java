package com.mpch.controlDeAsistencias_backend.repository;

import com.mpch.controlDeAsistencias_backend.model.Assistance;
import com.mpch.controlDeAsistencias_backend.model.Intern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AssistanceRepository extends JpaRepository<Assistance, UUID>{

//    boolean existsByInternAndCheckIn(Intern intern, LocalDate checkIn);
//
//    @Query(value = "CALL sp_assistance_find_today(:internId, :date)", nativeQuery = true)
//    Optional<Assistance> findTodayAssistance(@Param("internId") String internId, @Param("date") LocalDate date);
//
//    @Query(value = "CALL sp_assistance_find_by_date(:date)", nativeQuery = true)
//    List<Assistance> findAllByDate(@Param("date") LocalDate date);
//
//    @Query(value = "CALL sp_assistance_search_by_intern_name(:name, :page, :size)", nativeQuery = true)
//    List<Assistance> searchByInternName(@Param("name") String name, @Param("page") int page, @Param("size") int size);
//
//    @Query(value = "CALL sp_assistance_hours_worked_by_intern_on_date(:dni, :date)", nativeQuery = true)
//    double getHoursWorkedByInternOnDate(@Param("dni") String dni, @Param("date") LocalDate date);
//
//    @Query(value = "CALL sp_assistance_count_by_area_on_date(:areaName, :date)", nativeQuery = true)
//    int getAttendanceCountByAreaOnDate(@Param("areaName") String areaName, @Param("date") LocalDate date);
//
//    @Query(value = "CALL sp_assistance_hours_by_area(:areaName, :month, :year)", nativeQuery = true)
//    double getMonthlyHoursByArea(@Param("areaName") String areaName, @Param("month") int month, @Param("year") int year);
//
//    @Query(value = "CALL sp_assistance_hours_by_university(:universityName, :month, :year)", nativeQuery = true)
//    double getMonthlyHoursByUniversity(@Param("universityName") String universityName, @Param("month") int month, @Param("year") int year);
}
