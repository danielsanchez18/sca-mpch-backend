package com.mpch.controlDeAsistencias_backend.services;

import com.mpch.controlDeAsistencias_backend.model.User;

public interface AuthService {

    String login(String username, String password);

    User getCurrentUser(String token);

}
