package com.example.monetizemaisback.service;



import com.example.monetizemaisback.model.pergunta.Pergunta;
import com.example.monetizemaisback.repository.PerguntaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PerguntaService {

    private final PerguntaRepository repository;

    public Optional<Pergunta> getPerguntaByProgresso(Integer progresso){
        Optional<Pergunta> resultado = repository.findByProgresso(progresso);
        return resultado;
    }
}
