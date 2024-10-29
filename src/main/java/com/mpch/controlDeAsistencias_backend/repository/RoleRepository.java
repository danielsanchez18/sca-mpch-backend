package com.mpch.controlDeAsistencias_backend.repository;

import com.mpch.controlDeAsistencias_backend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID>{

    @Query(value = "CALL sp_role_search_by_name(:name, :page, :size)", nativeQuery = true)
    List<Role> searchByName (@Param("name") String name, @Param("page") int page, @Param("size") int size);

    @Query(value = "CALL sp_role_find_by_name(:name)", nativeQuery = true)
    Role findByName (@Param("name") String name);
}
