package com.example.monetizemaisback.controller;

import com.example.monetizemaisback.model.questions.Perguntas;
import com.example.monetizemaisback.model.user.PerguntaUsuarioLogin;
import com.example.monetizemaisback.model.user.UpdatePassword;
import com.example.monetizemaisback.model.user.Usuario;
import com.example.monetizemaisback.repository.InfoUsuarioRepository;
import com.example.monetizemaisback.repository.PerguntasRepository;
import com.example.monetizemaisback.repository.UsuarioRepository;
import com.example.monetizemaisback.request.UpdateProfilePictureRequest;
import com.example.monetizemaisback.request.UserLoginRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
            @ApiResponse(responseCode = "200", description = "User created successfully"),
            @ApiResponse(responseCode = "401", description = "Email or apelido already exists")
    })
    public ResponseEntity<String> createNewUser(@RequestBody Usuario novoUsuario) {
        logger.info("Creating new user with data: {}", novoUsuario);
        if (usuarioRepository.findByEmail(novoUsuario.getEmail()).isPresent()) {
            logger.warn("Email already exists: {}", novoUsuario.getEmail());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email já cadastrado");
        }

        if (usuarioRepository.findByApelido(novoUsuario.getApelido()).isPresent()) {
            logger.warn("Apelido already exists: {}", novoUsuario.getApelido());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Apelido já cadastrado");
        }

        usuarioRepository.save(novoUsuario);
        return ResponseEntity.ok("User created successfully");
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

    @GetMapping("/getAllUsersByPoints")
    public List<Usuario> getAllUsersByPoints() {
        logger.info("Fetching all users ordered by points");
        return usuarioRepository.findAllByOrderByPontosDesc();
    }

    @PostMapping("/infoUser")
    public PerguntaUsuarioLogin createNewInfoUser(@RequestBody PerguntaUsuarioLogin perguntaUsuarioLogin) {
        logger.info("Creating new info user with data: {}", perguntaUsuarioLogin);
        return perguntaUsuarioRepository.save(perguntaUsuarioLogin);
    }

    @PutMapping("/updatePassword")
    @Operation(summary = "Update user password", responses = {
            @ApiResponse(responseCode = "200", description = "Password updated successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<String> updatePassword(@RequestBody UpdatePassword updatePasswordRequest) {
        logger.info("Updating password for user ID: {}", updatePasswordRequest.getEmail());
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(updatePasswordRequest.getEmail());

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            usuario.setSenha(updatePasswordRequest.getPassword());
            usuarioRepository.save(usuario);

            logger.info("Password updated successfully for user ID: {}", updatePasswordRequest.getEmail());
            return ResponseEntity.ok("Password updated successfully");
        } else {
            logger.warn("User not found with ID: {}", updatePasswordRequest.getEmail());
            return ResponseEntity.status(404).body("User not found");
        }
    }

    @GetMapping("/checkUserByEmail/{email}")
    public ResponseEntity<Boolean> checkUserByEmail(@PathVariable String email) {
        logger.info("Checking if user exists with email: {}", email);
        boolean userExists = usuarioRepository.findByEmail(email).isPresent();
        return ResponseEntity.ok(userExists);
    }

    @PutMapping("/updateProfilePicture")
    @Operation(summary = "Update user profile picture", responses = {
            @ApiResponse(responseCode = "200", description = "Profile picture updated successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<String> updateProfilePicture(@RequestBody UpdateProfilePictureRequest updateRequest) {
        logger.info("Updating profile picture for user ID: {}", updateRequest.getId());

        Optional<Usuario> usuarioOptional = usuarioRepository.findById(updateRequest.getId());
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            usuario.setFotoPerfil(updateRequest.getFotoPerfil());
            usuarioRepository.save(usuario);

            logger.info("Profile picture updated successfully for user ID: {}", updateRequest.getId());
            return ResponseEntity.ok("Profile picture updated successfully");
        } else {
            logger.warn("User not found with ID: {}", updateRequest.getId());
            return ResponseEntity.status(404).body("User not found");
        }
    }
}