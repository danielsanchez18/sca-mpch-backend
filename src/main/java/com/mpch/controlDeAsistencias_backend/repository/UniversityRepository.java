package com.mpch.controlDeAsistencias_backend.repository;

import com.mpch.controlDeAsistencias_backend.model.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UniversityRepository extends JpaRepository<University, UUID> {

    @Query(value = "CALL sp_university_search_by_name(:name, :page, :size)", nativeQuery = true)
    List<University> searchByName (@Param("name") String name, @Param("page") int page, @Param("size") int size);

    @Query(value = "CALL sp_university_find_by_name(:name)", nativeQuery = true)
    Optional<University> findByName (@Param("name") String name);

    @Query(value = "CALL sp_university_find_by_acronym(:acronym)", nativeQuery = true)
    Optional<University> findByAcronym (@Param("acronym") String acronym);

}
