package com.example.monetizemaisback.model.questions;

import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Perguntas {
    @Id
    private Long id;
    private String pergunta;
    private Respostas respostas;
}
