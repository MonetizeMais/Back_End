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
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
public class UsuarioController {

    private final InfoUsuarioRepository perguntaUsuarioRepository;
    private final UsuarioRepository usuarioRepository;
    private final PerguntasRepository perguntasRepository;
    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @PostMapping("/newUser")
    @Operation(summary = "Create a new user", responses = {
            @ApiResponse(responseCode = "200", description = "User created successfully")
    })
    public Usuario createNewUser(@RequestBody Usuario novoUsuario) {
        logger.info("Creating new user with data: {}", novoUsuario);
        return usuarioRepository.save(novoUsuario);
    }

    @GetMapping("/getAllUsers")
    public List<Usuario> getAllUsers() {
        logger.info("Fetching all users");
        return usuarioRepository.findAll();
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<Usuario> getUserById(@PathVariable Long id) {
        logger.info("Fetching user with ID: {}", id);
        return usuarioRepository.findById(id)
                .map(user -> ResponseEntity.ok(user))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/newQuestion")
    public Perguntas createNewQuestion(@RequestBody Perguntas novaPergunta) {
        logger.info("Creating new question with data: {}", novaPergunta);
        return perguntasRepository.save(novaPergunta);
    }

    @GetMapping("/getQuestion/{id}")
    public ResponseEntity<Perguntas> getQuestionById(@PathVariable Long id) {
        logger.info("Fetching question with ID: {}", id);
        return perguntasRepository.findById(id)
                .map(question -> ResponseEntity.ok(question))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/updatePassword")
    public ResponseEntity<Usuario> updatePassword(@RequestBody UpdatePassword updatePassword) {
        Optional<Usuario> user = usuarioRepository.findById(updatePassword.getId());
        if (user.isPresent()) {
            Usuario existingUser = user.get();
            existingUser.setSenha(updatePassword.getPassword());
            return ResponseEntity.ok(usuarioRepository.save(existingUser));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/infoUser")
    public PerguntaUsuarioLogin createNewInfoUser(@RequestBody PerguntaUsuarioLogin perguntaUsuarioLogin) {
        logger.info("Creating new info user with data: {}", perguntaUsuarioLogin);
        return perguntaUsuarioRepository.save(perguntaUsuarioLogin);
    }
}
