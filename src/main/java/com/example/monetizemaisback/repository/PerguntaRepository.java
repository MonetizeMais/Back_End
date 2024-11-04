package com.example.monetizemaisback.repository;

import com.example.monetizemaisback.model.pergunta.Pergunta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerguntaRepository extends MongoRepository<Pergunta, String> {
    Optional<Pergunta> findByProgresso(Integer progresso);
}
