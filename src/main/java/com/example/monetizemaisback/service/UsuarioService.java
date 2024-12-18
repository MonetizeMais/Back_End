package com.example.monetizemaisback.service;


import com.example.monetizemaisback.request.*;
import com.example.monetizemaisback.model.user.Usuario;
import com.example.monetizemaisback.repository.UsuarioRepository;
import com.example.monetizemaisback.response.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    public ResponseEntity<String> createNewUser(Usuario novoUsuario) {
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

    public List<Usuario> getAllUsers() {
        logger.info("Fetching all users");
        return usuarioRepository.findAll();
    }

    public ResponseEntity<Usuario> getUserById(Long id) {
        logger.info("Fetching user with ID: {}", id);
        return usuarioRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Usuario> getUserByEmail(String email) {
        logger.info("Fetching user with email: {}", email);
        return usuarioRepository.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    public ResponseEntity<String> login(UserLoginRequest userLoginRequest) {
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

    public List<Usuario> getAllUsersByPoints() {
        logger.info("Fetching all users ordered by points");
        return usuarioRepository.findAllByOrderByPontosDesc();
    }

    public ResponseEntity<String> updateStats(String email, UpdateStatsRequest statsRequest) {
        logger.info("Updating password for user ID: {}", email);
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);

        if(usuarioOptional.isPresent()) {
            Usuario user = usuarioOptional.get();
            user.setVida(statsRequest.getVida());
            user.setCoin(statsRequest.getCoin());
            usuarioRepository.save(user);
            logger.info("Password updated successfully for user ID: {}", email);
            return ResponseEntity.ok("Password updated successfully");
        } else {
            logger.warn("User not found with ID: {}", email);
            return ResponseEntity.status(404).body("User not found");
        }
    }

    public ResponseEntity<String> updatePassword(UpdatePassword updatePasswordRequest) {
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

    public ResponseEntity<ResponseMessage> updateProfilePicture(UpdateProfilePictureRequest updateRequest) {
        logger.info("Updating profile picture for user ID: {}", updateRequest.getEmail());

        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(updateRequest.getEmail());
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            usuario.setFotoPerfil(updateRequest.getFotoPerfil());

            usuarioRepository.save(usuario);
            logger.info("Profile picture updated successfully for user ID: {}", updateRequest.getEmail());
            return ResponseEntity.ok(new ResponseMessage("Profile picture updated successfully"));
        } else {
            logger.warn("User not found with ID: {}", updateRequest.getEmail());
            return ResponseEntity.status(404).body(new ResponseMessage("User not found"));
        }
    }


    public boolean checkUserByEmail(String email) {
        logger.info("Checking if user exists with email: {}", email);
        return usuarioRepository.findByEmail(email).isPresent();
    }

    public ResponseEntity<String> updateEmailAndApelido(Long id, UpdateEmailApelidoRequest updateRequest) {
        logger.info("Updating email and apelido for user ID: {}", id);

        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isEmpty()) {
            logger.warn("User not found with ID: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        Usuario usuario = usuarioOptional.get();

        usuario.setEmail(updateRequest.getEmail());
        usuario.setApelido(updateRequest.getApelido());
        usuarioRepository.save(usuario);

        logger.info("Email and apelido updated successfully for user ID: {}", id);
        return ResponseEntity.ok("Email and apelido updated successfully");
    }

    public ResponseEntity<String> updateLife(String email, Integer life) {
        logger.info("Updating life for user ID: {}", email);

        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);
        if (usuarioOptional.isEmpty()) {
            logger.warn("User not found with ID: {}", email);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        Usuario usuario = usuarioOptional.get();

        usuario.setVida(life);
        usuarioRepository.save(usuario);

        return ResponseEntity.ok("Life  updated successfully");
    }

    public ResponseEntity<String> updateCoin(String email, Integer coin) {

        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);
        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        Usuario usuario = usuarioOptional.get();

        usuario.setCoin(coin);
        usuarioRepository.save(usuario);

        return ResponseEntity.ok("Coin updated successfully");
    }
}
