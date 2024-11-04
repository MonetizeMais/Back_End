package com.example.monetizemaisback.service;


import com.example.monetizemaisback.model.conteudo.Conteudo;
import com.example.monetizemaisback.repository.ConteudoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConteudoService {
    private final ConteudoRepository conteudoRepository;

    public List<Conteudo> getAllConteudos() {
        List<Conteudo> conteudos = conteudoRepository.findAll();

        System.out.println("Conteúdos encontrados: " + conteudos);
        return conteudos;
    }

    public Optional<Conteudo> buscarPorProgresso(Integer progresso) {
        Optional<Conteudo> resultado = conteudoRepository.findByProgresso(progresso);
        return resultado;
    }
}
