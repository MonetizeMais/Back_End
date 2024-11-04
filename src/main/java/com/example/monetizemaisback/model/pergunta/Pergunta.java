package com.example.monetizemaisback.model.pergunta;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@Document
public class Pergunta {
    private Integer progresso;
    private String pergunta;
    private String tema;
    private List<Alternativa> resposta;
}