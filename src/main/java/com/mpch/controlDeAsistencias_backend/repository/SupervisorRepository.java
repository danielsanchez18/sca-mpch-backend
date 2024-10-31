package com.mpch.controlDeAsistencias_backend.repository;

import com.mpch.controlDeAsistencias_backend.model.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SupervisorRepository extends JpaRepository<Supervisor, String>{

    @Query(value = "CALL sp_supervisor_search_by_name(:name, :page, :size)", nativeQuery = true)
    List<Supervisor> searchByName (@Param("name") String name, @Param("page") int page, @Param("size") int size);

    @Query(value = "CALL sp_supervisor_find_by_area(:area, :page, :size)", nativeQuery = true)
    List<Supervisor> findByArea (@Param("area") String area, @Param("page") int page, @Param("size") int size);

}
