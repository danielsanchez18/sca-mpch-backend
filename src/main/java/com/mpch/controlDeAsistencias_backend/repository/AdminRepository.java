package com.mpch.controlDeAsistencias_backend.repository;

import com.mpch.controlDeAsistencias_backend.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin, String> {

    @Query(value = "CALL sp_admin_search_by_name(:name, :page, :size)", nativeQuery = true)
    List<Admin> searchByName (@Param("name") String name, @Param("page") int page, @Param("size") int size);

}
