package com.mpch.controlDeAsistencias_backend.repository;

import com.mpch.controlDeAsistencias_backend.model.Intern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InternRepository extends JpaRepository<Intern, String> {

    long countByArea_IdArea(UUID idArea);

    @Query(value = "CALL sp_intern_search_by_name(:name, :page, :size)", nativeQuery = true)
    List<Intern> searchByName(@Param("name") String name, @Param("page") int page, @Param("size") int size);

    @Query(value = "CALL sp_intern_find_by_area(:area, :page, :size)", nativeQuery = true)
    List<Intern> findByArea(@Param("area") String area, @Param("page") int page, @Param("size") int size);

    @Query(value = "CALL sp_intern_find_by_university(:university, :page, :size)", nativeQuery = true)
    List<Intern> findByUniversity(@Param("university") String university, @Param("page") int page, @Param("size") int size);

//    Optional<Intern> findByDni(String dni);
}
