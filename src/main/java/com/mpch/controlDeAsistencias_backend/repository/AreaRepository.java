package com.mpch.controlDeAsistencias_backend.repository;

import com.mpch.controlDeAsistencias_backend.model.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AreaRepository extends JpaRepository<Area, UUID> {

    @Query(value = "CALL sp_area_search_by_name(:name, :page, :size)", nativeQuery = true)
    List<Area> searchByName (@Param("name") String name, @Param("page") int page, @Param("size") int size);

    @Query(value = "CALL sp_area_find_by_name(:name)", nativeQuery = true)
    Area findByName (@Param("name") String name);

}
