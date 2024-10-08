package com.example.monetizemaisback.model.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Usuario")
@Data
@NoArgsConstructor
@Getter
@Setter
public class Usuario {

    @Id
    @Column(name = "ncdusuario")
    private Long id;

    @Column(name = "cnmusuario", nullable = false, length = 200)
    private String nome;

    @Column(name = "cemail", nullable = false, length = 200)
    private String email;

    @Column(name = "capelido", nullable = false, length = 50)
    private String apelido;

    @Column(name = "csenha", nullable = false, length = 255)
    private String senha;

    @Column(name = "iofensiva")
    private Integer ofensiva;

    @Column(name = "ivida")
    private Integer vida;

    @Column(name = "icoin")
    private Integer coin;

    @Column(name = "cfotoperfil", length = 255)
    private String fotoPerfil;
}
