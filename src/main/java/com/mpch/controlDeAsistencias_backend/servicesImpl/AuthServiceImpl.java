package com.mpch.controlDeAsistencias_backend.servicesImpl;

import com.mpch.controlDeAsistencias_backend.model.User;
import com.mpch.controlDeAsistencias_backend.repository.AdminRepository;
import com.mpch.controlDeAsistencias_backend.repository.SupervisorRepository;
import com.mpch.controlDeAsistencias_backend.services.AuthService;
import com.mpch.controlDeAsistencias_backend.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private SupervisorRepository supervisorRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public String login(String dni, String password) {

        // Buscar Admin
        var admin = adminRepository.findByUser_Dni(dni);
        if (admin.isPresent() && passwordMatches(password, admin.get().getPassword())) {
            return jwtUtils.generateToken(dni, "Admin");
        }

        // Buscar Supervisor
        var supervisor = supervisorRepository.findByUser_Dni(dni);
        if (supervisor.isPresent() && passwordMatches(password, supervisor.get().getPassword())) {
            return jwtUtils.generateToken(dni, "Supervisor");
        }

        throw new RuntimeException("Credenciales inválidas");

    }

    private boolean passwordMatches(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(rawPassword, encodedPassword);
    }

    @Override
    public User getCurrentUser(String token) {
        String dni = jwtUtils.extractDni(token);
        return adminRepository.findByUser_Dni(dni)
                .map(admin -> admin.getUser())
                .or(() -> supervisorRepository.findByUser_Dni(dni).map(supervisor -> supervisor.getUser()))
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
}
