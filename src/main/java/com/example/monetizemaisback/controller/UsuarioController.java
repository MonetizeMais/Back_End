package com.example.monetizemaisback.controller;

import com.example.monetizemaisback.model.questions.Perguntas;
import com.example.monetizemaisback.model.user.PerguntaUsuarioLogin;
import com.example.monetizemaisback.model.user.UpdatePassword;
import com.example.monetizemaisback.model.user.Usuario;
import com.example.monetizemaisback.repository.InfoUsuarioRepository;
import com.example.monetizemaisback.repository.PerguntasRepository;
import com.example.monetizemaisback.repository.UsuarioRepository;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
public class UsuarioController {

    private final InfoUsuarioRepository perguntaUsuarioRepository;
    private final UsuarioRepository usuarioRepository;
    private final PerguntasRepository perguntasRepository;
    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @PostMapping("/newUser")
    @Operation(summary = "Exemplo de operação", responses = {
            @ApiResponse(responseCode = "200", description = "Operação bem-sucedida")
    })
    public Usuario newUsuario(@RequestBody Usuario novoUsuario) {
        logger.info("Creating new user with data: {}", novoUsuario);
        return usuarioRepository.save(novoUsuario);
    }

    @GetMapping("/getAllUsers")
    public List<Usuario> getAllUsuarios() {
        logger.info("Fetching all users");
        return usuarioRepository.findAll();
    }

    @GetMapping("/getUser/{id}")
    public Optional<Usuario> getUsuarioById(@PathVariable Long id) {
        logger.info("Fetching user with ID: {}", id);
        return usuarioRepository.findById(id);
    }

    @PostMapping("/newQuestion")
    public Perguntas newPergunta(@RequestBody Perguntas novaPergunta) {
        logger.info("Creating new question with data: {}", novaPergunta);
        return perguntasRepository.save(novaPergunta);
    }
    @GetMapping("/getQuestion/{id}")
    public Optional<Perguntas> getPerguntaById(@PathVariable Long id) {
        logger.info("Fetching question with ID: {}", id);
        return perguntasRepository.findById(id);
    }

    @PostMapping("/updatePassword")
    public Optional<Usuario> newPassword(@RequestBody UpdatePassword updatePassword) {
        Optional<Usuario> user = usuarioRepository.findById(updatePassword.getId());
        if (user.isPresent()) {
            Usuario existingUser = user.get();
            existingUser.setSenha(updatePassword.getPassword());
            return Optional.of(usuarioRepository.save(existingUser));
        }
        return Optional.empty();
    }

    @PostMapping("/infoUser")
    public PerguntaUsuarioLogin newInfoUser(@RequestBody PerguntaUsuarioLogin perguntaUsuarioLogin) {
        logger.info("Creating new info user with data: {}", perguntaUsuarioLogin);
        return perguntaUsuarioRepository.save(perguntaUsuarioLogin);
    }
}
