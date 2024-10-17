package com.example.monetizemaisback.controller;

import com.example.monetizemaisback.model.questions.Perguntas;
import com.example.monetizemaisback.model.user.PerguntaUsuarioLogin;
import com.example.monetizemaisback.model.user.UpdatePassword;
import com.example.monetizemaisback.model.user.Usuario;
import com.example.monetizemaisback.repository.InfoUsuarioRepository;
import com.example.monetizemaisback.repository.PerguntasRepository;
import com.example.monetizemaisback.repository.UsuarioRepository;


import com.example.monetizemaisback.request.UserLoginRequest;
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
        Optional<Usuario> user = usuarioRepository.findById(updatePassword.getNCdUsuario());
        if (user.isPresent()) {
            Usuario existingUser = user.get();
            existingUser.setSenha(updatePassword.getPassword());
            return ResponseEntity.ok(usuarioRepository.save(existingUser));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/userLogin")
    public ResponseEntity<String> login(@RequestBody UserLoginRequest userLoginRequest) {
        logger.info("Attempting login with email/apelido: {}", userLoginRequest.getEmail());
        Optional<Usuario> userOptional = usuarioRepository.findByEmailOrApelido(userLoginRequest.getEmail(), userLoginRequest.getEmail());

        if (userOptional.isPresent()) {
            Usuario user = userOptional.get();
            if (user.getSenha().equals(userLoginRequest.getSenha())) {
                logger.info("Login successful for email/apelido: {}", userLoginRequest.getEmail());
                return ResponseEntity.ok("Login successful");
            } else {
                logger.warn("Login failed: incorrect password for email/apelido: {}", userLoginRequest.getEmail());
                return ResponseEntity.status(401).body("Incorrect password");
            }
        } else {
            logger.warn("Login failed: no user found with email/apelido: {}", userLoginRequest.getEmail());
            return ResponseEntity.status(404).body("User not found");
        }
    }

    @PostMapping("/infoUser")
    public PerguntaUsuarioLogin createNewInfoUser(@RequestBody PerguntaUsuarioLogin perguntaUsuarioLogin) {
        logger.info("Creating new info user with data: {}", perguntaUsuarioLogin);
        return perguntaUsuarioRepository.save(perguntaUsuarioLogin);
    }
}
