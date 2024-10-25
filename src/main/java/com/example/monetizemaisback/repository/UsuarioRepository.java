package com.example.monetizemaisback.repository;

import com.example.monetizemaisback.model.user.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Optional<Usuario> findByEmailOrApelido(String email, String apelido);

    List<Usuario> findAllByOrderByPontosDesc();

    Optional<Usuario> findByEmail(String email);

}

