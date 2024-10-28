package com.example.monetizemaisback.controller;

import com.example.monetizemaisback.model.questions.Perguntas;
import com.example.monetizemaisback.model.user.PerguntaUsuarioLogin;
import com.example.monetizemaisback.model.user.UpdatePassword;
import com.example.monetizemaisback.model.user.Usuario;
import com.example.monetizemaisback.repository.UsuarioRepository;
import com.example.monetizemaisback.request.UpdateProfilePictureRequest;
import com.example.monetizemaisback.request.UserLoginRequest;
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

    @GetMapping("/getUser/{email}")
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

    @PostMapping("/newQuestion")
    public Perguntas createNewQuestion(@RequestBody Perguntas novaPergunta) {
        return usuarioService.createNewQuestion(novaPergunta);
    }

    @GetMapping("/getQuestion/{id}")
    public ResponseEntity<Perguntas> getQuestionById(@PathVariable Long id) {
        return usuarioService.getQuestionById(id);
    }

    @PostMapping("/userLogin")
    public ResponseEntity<String> login(@RequestBody UserLoginRequest userLoginRequest) {
        return usuarioService.login(userLoginRequest);
    }

    @GetMapping("/getAllUsersByPoints")
    public List<Usuario> getAllUsersByPoints() {
        return usuarioService.getAllUsersByPoints();
    }

    @PostMapping("/infoUser")
    public PerguntaUsuarioLogin createNewInfoUser(@RequestBody PerguntaUsuarioLogin perguntaUsuarioLogin) {
        return usuarioService.createNewInfoUser(perguntaUsuarioLogin);
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

    @PutMapping("/updateProfilePicture")
    @Operation(summary = "Update user profile picture")
    public ResponseEntity<String> updateProfilePicture(@RequestBody UpdateProfilePictureRequest updateRequest) {
        return usuarioService.updateProfilePicture(updateRequest);
    }
}
