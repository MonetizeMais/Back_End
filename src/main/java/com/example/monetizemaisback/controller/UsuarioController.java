package com.example.monetizemaisback.controller;

import com.example.monetizemaisback.model.Usuario;
import com.example.monetizemaisback.repository.UsuarioRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
@Validated
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;
    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @PostMapping("/novo")
    public Usuario criarNovoUsuario(@Valid @RequestBody Usuario novoUsuario) {
        logger.info("Creating new user with data: {}", novoUsuario);
        return usuarioRepository.save(novoUsuario);
    }

    @GetMapping
    public List<Usuario> getAllUsuarios() {
        logger.info("Fetching all users");
        return usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Usuario> getUsuarioById(@PathVariable Long id) {
        logger.info("Fetching user with ID: {}", id);
        return usuarioRepository.findById(id);
    }
}
