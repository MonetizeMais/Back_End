package com.example.monetizemaisback.model.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "perguntausuariologin")
@Getter
@Setter
@NoArgsConstructor
public class PerguntaUsuarioLogin {

    @Column(name = "ccomoconheceu")
    private String ccomoconheceu;

    @Column(name = "cnivelconhecimento")
    private String cnivelconhecimento;

    @Column(name = "cmotivacao")
    private String cmotivacao;

    @Column(name = "cpreferencias")
    private String cpreferencias;

    @Id
    @Column(name = "ncdusuario")
    private int ncdusuario;
}


