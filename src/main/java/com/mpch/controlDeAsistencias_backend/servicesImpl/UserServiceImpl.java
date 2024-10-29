package com.mpch.controlDeAsistencias_backend.servicesImpl;

import com.mpch.controlDeAsistencias_backend.model.User;
import com.mpch.controlDeAsistencias_backend.repository.UserRepository;
import com.mpch.controlDeAsistencias_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private void validateUser(UUID idUser, User user) {

        userRepository.findByDni(user.getDni()).ifPresent(existingUser -> {
            if (idUser == null || !existingUser.getIdUser().equals(idUser)) {
                throw new RuntimeException("Ya existe un usuario con el mismo DNI");
            }
        });

        if (user.getAge() <= 18) {
            throw new RuntimeException("El usuario debe ser mayor de 18 años");
        }

    }

    @Override
    public User save(User user) {
        validateUser(user.getIdUser(), user);

        Date currentDate = new Date();
        user.setCreatedAt(currentDate);
        user.setUpdatedAt(currentDate);

        return userRepository.save(user);
    }

    @Override
    public User findUserById(UUID idUser) {
        return userRepository.findById(idUser).orElseThrow(
                () -> new RuntimeException("Usuario no encontrado")
        );
    }

    @Override
    public Page<User> findAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public List<User> findUsersByRole(String role, int page, int size) {
        return userRepository.findByRole(role, page, size);
    }

    @Override
    public List<User> searchUsersByName(String name, int page, int size) {
        return userRepository.searchByName(name, page, size);
    }

    @Override
    public List<User> searchUsersByDni(String dni, int page, int size) {
        return userRepository.searchByDni(dni, page, size);
    }

    @Override
    public List<User> findUsersEnabled(int page, int size) {
        return userRepository.findEnabled(page, size);
    }

    @Override
    public List<User> findUsersDisabled(int page, int size) {
        return userRepository.findDisabled(page, size);
    }

    @Override
    public Long getTotalUsers() {
        return userRepository.count();
    }

    @Override
    public User updateUser(UUID idUser, User user) {
        User existingUser = findUserById(idUser);
        validateUser(idUser, user);

        existingUser.setName(user.getName());
        existingUser.setLastname(user.getLastname());
        existingUser.setDni(user.getDni());
        existingUser.setAge(user.getAge());
        existingUser.setPhoto(user.getPhoto());
        existingUser.setStatus(user.isStatus());

        existingUser.setUpdatedAt(new Date());

        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(UUID idUser) {
        userRepository.deleteById(idUser);
    }
}
