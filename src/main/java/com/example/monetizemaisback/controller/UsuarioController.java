package com.example.monetizemaisback.controller;


import com.example.monetizemaisback.model.user.PerguntaUsuarioLogin;
import com.example.monetizemaisback.repository.PerguntaUsuarioRepository;
import com.example.monetizemaisback.request.UpdateEmailApelidoRequest;
import com.example.monetizemaisback.request.UpdatePassword;
import com.example.monetizemaisback.request.UpdateProfilePictureRequest;
import com.example.monetizemaisback.request.UserLoginRequest;
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

    private final PerguntaUsuarioRepository perguntaUsuarioRepository;

    private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    @PostMapping("/newUser")
    @Operation(summary = "Create a new user")
    public ResponseEntity<String> createNewUser(@RequestBody Usuario novoUsuario) {
        return usuarioService.createNewUser(novoUsuario);
    }

    @PostMapping("/criarPergutnaUsuarioRepository")
    public PerguntaUsuarioLogin createNewInfoUser(@RequestBody PerguntaUsuarioLogin perguntaUsuarioLogin) {
        return perguntaUsuarioRepository.save(perguntaUsuarioLogin);
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

    @PutMapping("/updateLife/{email}/{life}")
    public ResponseEntity<String> updateLife(
            @PathVariable("email") String email,
            @PathVariable("life") Integer life) {
        return usuarioService.updateLife(email, life);
    }

    @PutMapping("/updateCoin/{email}/{coin}")
    public ResponseEntity<String> updateCoin(
            @PathVariable("email") String email,
            @PathVariable("coin") Integer coin) {
        return usuarioService.updateCoin(email, coin);
    }

    @PutMapping("/updateProgresso/{email}/{progresso}")
    @Operation(summary = "Update user progress")
    public ResponseEntity<String> updateProgresso(
            @PathVariable("email") String email,
            @PathVariable("progresso") Double progresso) {

        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            usuario.setProgresso(progresso);
            usuarioRepository.save(usuario);
            return ResponseEntity.ok("Progresso atualizado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }
    }

    @PutMapping("/updatePontos/{email}/{pontos}")
    @Operation(summary = "Update user progress")
    public ResponseEntity<String> updatePontos(
            @PathVariable("email") String email,
            @PathVariable("pontos") Integer pontos) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            usuario.setPontos(pontos);
            usuarioRepository.save(usuario);
            return ResponseEntity.ok("pontos atualizado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }
    }

    @PutMapping("/updateEmail/{email}/{newEmail}")
    @Operation(summary = "Update user progress")
    public ResponseEntity<String> updateEmail(
            @PathVariable("email") String email,
            @PathVariable("newEmail") String newEmail) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            usuario.setEmail(newEmail);
            usuarioRepository.save(usuario);
            return ResponseEntity.ok("email atualizado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }
    }

    @PutMapping("/updateApelido/{email}/{apelido}")
    @Operation(summary = "Update user progress")
    public ResponseEntity<String> updateApelido(
            @PathVariable("email") String email,
            @PathVariable("apelido") String apelido) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            usuario.setApelido(apelido);
            usuarioRepository.save(usuario);
            return ResponseEntity.ok("apelido atualizado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }
    }
}