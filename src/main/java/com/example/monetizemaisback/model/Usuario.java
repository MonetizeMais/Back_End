package com.example.monetizemaisback.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Usuario {

    @Id
    @Column(name = "ncdusuario")
    private Long ncdusuario;

    @NotNull
    @Column(name = "cnmusuario", nullable = false)
    private String cnmusuario;

    @NotNull
    @Column(name = "cemail", nullable = false)
    private String cemail;

    @NotNull
    @Column(name = "capelido", nullable = false)
    private String capelido;

    @NotNull
    @Column(name = "csenha", nullable = false)
    private String csenha;

    @Column(name = "iofensiva")
    private int iofensiva;

    @Column(name = "ivida")
    private int ivida;

    @Column(name = "icoin")
    private int icoin;

    @Column(name = "cfotoperfil")
    private String cfotoperfil;
}
