package com.example.monetizemaisback.model.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Timestamp;

@Entity
@Table(name = "LogUsuario")
@Data
@NoArgsConstructor
public class LogUsuario {

    @Id
    @Column(name = "ncdLog", precision = 10, scale = 0)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ncdUsuario", nullable = false)
    private Usuario usuario;

    @Column(name = "dalteracao", nullable = false)
    private Timestamp alteracao;

    @Column(name = "cacao", length = 255)
    private String acao;

    @Column(name = "cdescricao", length = 255)
    private String descricao;
}