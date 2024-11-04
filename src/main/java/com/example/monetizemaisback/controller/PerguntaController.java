package com.example.monetizemaisback.controller;



import com.example.monetizemaisback.model.pergunta.Pergunta;
import com.example.monetizemaisback.service.PerguntaService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;


@Controller
@RequiredArgsConstructor
public class PerguntaController {


    private final PerguntaService service;

    @GetMapping("/getPergunta/{progresso}")
    @Operation(summary = "Get all contents")
    public ResponseEntity<Optional<Pergunta>> getAllConteudos(@PathVariable("progresso") Integer progresso) {
        Optional<Pergunta> conteudos = service.getPerguntaByProgresso(progresso);
        if (conteudos.isPresent()) {
            return ResponseEntity.ok(conteudos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
