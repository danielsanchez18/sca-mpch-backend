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

    List<User> findUsersByRole(String role, int page, int size);

    List<User> searchUsersByName(String name, int page, int size);

    List<User> searchUsersByDni(String dni, int page, int size);

    List<User> findUsersEnabled(int page, int size);

    List<User> findUsersDisabled(int page, int size);

    Long getTotalUsers();

    User updateUser(UUID idUser, User user);

    void deleteUser(UUID idUser);

}
