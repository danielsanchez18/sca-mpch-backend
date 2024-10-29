package com.mpch.controlDeAsistencias_backend.repository;

import com.mpch.controlDeAsistencias_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Query(value = "CALL sp_user_find_by_dni(:dni)", nativeQuery = true)
    Optional<User> findByDni(@Param("dni") String dni);

    @Query(value = "CALL sp_user_search_by_role(:role, :page, :size)", nativeQuery = true)
    List<User> findByRole(@Param("role") String role, @Param("page") int page, @Param("size") int size);

    @Query(value = "CALL sp_user_search_by_name(:name, :page, :size)", nativeQuery = true)
    List<User> searchByName(@Param("name") String name, @Param("page") int page, @Param("size") int size);

    @Query(value = "CALL sp_user_search_by_dni(:dni, :page, :size)", nativeQuery = true)
    List<User> searchByDni(@Param("dni") String dni, @Param("page") int page, @Param("size") int size);

    @Query(value = "CALL sp_user_find_by_status(true, :page, :size)", nativeQuery = true)
    List<User> findEnabled(@Param("page") int page, @Param("size") int size);

    @Query(value = "CALL sp_user_find_by_status(false, :page, :size)", nativeQuery = true)
    List<User> findDisabled(@Param("page") int page, @Param("size") int size);

}
