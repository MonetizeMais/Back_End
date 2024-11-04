package com.example.monetizemaisback.model.conteudo;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.ArrayList;
import java.util.List;

@Data
@Document("conteudo")
@NoArgsConstructor
@AllArgsConstructor
public class Conteudo {

    @MongoId(FieldType.OBJECT_ID)
    private String id;

    @Field(name = "progresso")
    private Integer progresso;

    @Field(name = "conteudo")
    private String conteudo;

    @Field(name = "descricao")
    private String descricao;

    @Field(name = "conversa")
    private List<Conversa> conversa;


}
