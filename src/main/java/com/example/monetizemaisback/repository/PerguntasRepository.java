package com.example.monetizemaisback.repository;

import com.example.monetizemaisback.model.questions.Perguntas;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerguntasRepository extends MongoRepository<Perguntas, Long> {
}
