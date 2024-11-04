package com.example.monetizemaisback.repository;


import com.example.monetizemaisback.model.conteudo.Conteudo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConteudoRepository extends MongoRepository<Conteudo, String> {
    Optional<Conteudo> findByProgresso(Integer progresso);
}
