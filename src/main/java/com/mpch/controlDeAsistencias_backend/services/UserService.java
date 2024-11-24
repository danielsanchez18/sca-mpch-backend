package com.mpch.controlDeAsistencias_backend.services;

import com.mpch.controlDeAsistencias_backend.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User save(User user);

    User findUserById(UUID idUser);

    Page<User> findAllUsers(Pageable pageable);

    Page<User> searchUsersByName(String name, Pageable pageable);

    Page<User> findUsersByRole(Long idRole, Pageable pageable);

    Page<User> searchUsersByDni(String dni, Pageable pageable);

    Page<User> findUsersByStatus(boolean status, Pageable pageable);

    Long getTotalUsers();

    User updateUser(UUID idUser, User user);

    void deleteUser(UUID idUser);

}
