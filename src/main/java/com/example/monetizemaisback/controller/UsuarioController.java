package com.example.monetizemaisback.controller;


import com.example.monetizemaisback.request.*;
import com.example.monetizemaisback.service.ConteudoService;
import com.example.monetizemaisback.model.conteudo.Conteudo;
import com.example.monetizemaisback.model.user.Usuario;
import com.example.monetizemaisback.repository.UsuarioRepository;
import com.example.monetizemaisback.response.ResponseMessage;
import com.example.monetizemaisback.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Validated
public class UsuarioController {

    private final UsuarioService usuarioService;

    private final UsuarioRepository usuarioRepository;

    private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    @PostMapping("/newUser")
    @Operation(summary = "Create a new user")
    public ResponseEntity<String> createNewUser(@RequestBody Usuario novoUsuario) {
        return usuarioService.createNewUser(novoUsuario);
    }

    @GetMapping("/getAllUsers")
    public List<Usuario> getAllUsers() {
        return usuarioService.getAllUsers();
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<Usuario> getUserById(@PathVariable Long id) {
        return usuarioService.getUserById(id);
    }

    @GetMapping("/getUserByEmail/{email}")
    public ResponseEntity<Usuario> getUserByEmail(@PathVariable String email) {
        logger.info("Fetching user with email: {}", email);
        try {
            return usuarioRepository.findByEmail(email)
                    .map(user -> ResponseEntity.ok(user))
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            logger.error("Error fetching user with email: {}", email, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/userLogin")
    public ResponseEntity<String> login(@RequestBody UserLoginRequest userLoginRequest) {
        return usuarioService.login(userLoginRequest);
    }

    @GetMapping("/getAllUsersByPoints")
    public List<Usuario> getAllUsersByPoints() {
        return usuarioService.getAllUsersByPoints();
    }

    @PutMapping("/updatePassword")
    @Operation(summary = "Update user password")
    public ResponseEntity<String> updatePassword(@RequestBody UpdatePassword updatePasswordRequest) {
        return usuarioService.updatePassword(updatePasswordRequest);
    }

    @GetMapping("/checkUserByEmail/{email}")
    public ResponseEntity<Boolean> checkUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(usuarioService.checkUserByEmail(email));
    }


    @GetMapping("/findUserByEmail/{email}")
    public ResponseEntity<Optional<Usuario>> findUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(usuarioRepository.findByEmail(email));
    }

    @PutMapping("/updateProfilePicture")
    @Operation(summary = "Update user profile picture")
    public ResponseEntity<ResponseMessage> updateProfilePicture(@RequestBody UpdateProfilePictureRequest updateRequest) {
        return usuarioService.updateProfilePicture(updateRequest);
    }

    @PutMapping("/updateEmailApelido/{id}")
    @Operation(summary = "Update user email and apelido")
    public ResponseEntity<String> updateUserEmailAndApelido(
            @PathVariable Long id,
            @RequestBody UpdateEmailApelidoRequest updateRequest) {
        return usuarioService.updateEmailAndApelido(id, updateRequest);
    }


    @PutMapping("/updateProgresso/{id}")
    @Operation(summary = "Update user progress")
    public ResponseEntity<String> updateProgresso(
            @PathVariable Long id,
            @RequestBody UpdateProgressoRequest updateProgressoRequest) {

        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            usuario.setProgresso(updateProgressoRequest.getProgresso());
            usuarioRepository.save(usuario);
            return ResponseEntity.ok("Progresso atualizado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }
    }
}