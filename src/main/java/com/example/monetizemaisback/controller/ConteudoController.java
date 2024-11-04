package com.example.monetizemaisback.controller;


import com.example.monetizemaisback.model.conteudo.Conteudo;
import com.example.monetizemaisback.service.ConteudoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ConteudoController {

    private final ConteudoService conteudoService;

    @GetMapping("/getAllConteudos")
    @Operation(summary = "Get all contents")
    public ResponseEntity<List<Conteudo>> getAllConteudos() {
        List<Conteudo> conteudos = conteudoService.getAllConteudos();
        return ResponseEntity.ok(conteudos);
    }

    @GetMapping("/getConteudo/{id}")
    @Operation(summary = "Get all contents")
    public ResponseEntity<Optional<Conteudo>> getAllConteudos(@PathVariable("id") Integer id) {
        Optional<Conteudo> conteudos = conteudoService.buscarPorProgresso(id);
        return ResponseEntity.ok(conteudos);
    }

}
