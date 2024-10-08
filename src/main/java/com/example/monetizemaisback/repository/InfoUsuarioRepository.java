package com.example.monetizemaisback.repository;


import com.example.monetizemaisback.model.user.PerguntaUsuarioLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoUsuarioRepository extends JpaRepository<PerguntaUsuarioLogin,Long> {
}
