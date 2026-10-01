package com.vetturno.service;

import com.vetturno.dto.AuthResponse;
import com.vetturno.dto.LoginRequest;
import com.vetturno.dto.RegistroRequest;
import com.vetturno.model.Rol;
import com.vetturno.model.Usuario;
import com.vetturno.repository.UsuarioRepository;
import com.vetturno.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtService jwtService) {

        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public AuthResponse registrar(RegistroRequest request) {

        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }

        Usuario usuario = new Usuario();

        usuario.setEmail(request.getEmail());
        usuario.setPassword(
                passwordEncoder.encode(request.getPassword())
        );
        usuario.setRol(Rol.USER);

        usuarioRepository.save(usuario);

        String token = jwtService.generarToken(usuario.getEmail());

        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        String token = jwtService.generarToken(request.getEmail());

        return new AuthResponse(token);
    }
}