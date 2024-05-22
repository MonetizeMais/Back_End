package com.example.monetizemaisback.controller;

import com.example.monetizemaisback.model.Usuario;
import com.example.monetizemaisback.repository.UsuarioRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.validation.annotation.Validated;

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
}
