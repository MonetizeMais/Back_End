package com.example.monetizemaisback;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jdk.jfr.Enabled;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int nCdUsuario;
    private String cNmUsuario;
    private String cEmail;
    private String cApelido;
    private String cSenha;
    private int iOfensiva;
    private int iVida;
    private int iCoin;
    private String cFotoPerfil;

    public int getnCdUsuario() {
        return nCdUsuario;
    }

    public void setnCdUsuario(int nCdUsuario) {
        this.nCdUsuario = nCdUsuario;
    }

    public String getcNmUsuario() {
        return cNmUsuario;
    }

    public void setcNmUsuario(String cNmUsuario) {
        this.cNmUsuario = cNmUsuario;
    }

    public String getcEmail() {
        return cEmail;
    }

    public void setcEmail(String cEmail) {
        this.cEmail = cEmail;
    }

    public String getcApelido() {
        return cApelido;
    }

    public void setcApelido(String cApelido) {
        this.cApelido = cApelido;
    }

    public String getcSenha() {
        return cSenha;
    }

    public void setcSenha(String cSenha) {
        this.cSenha = cSenha;
    }

    public int getiOfensiva() {
        return iOfensiva;
    }

    public void setiOfensiva(int iOfensiva) {
        this.iOfensiva = iOfensiva;
    }

    public int getiVida() {
        return iVida;
    }

    public void setiVida(int iVida) {
        this.iVida = iVida;
    }

    public int getiCoin() {
        return iCoin;
    }

    public void setiCoin(int iCoin) {
        this.iCoin = iCoin;
    }

    public String getcFotoPerfil() {
        return cFotoPerfil;
    }

    public void setcFotoPerfil(String cFotoPerfil) {
        this.cFotoPerfil = cFotoPerfil;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nCdUsuario=" + nCdUsuario +
                ", cNmUsuario='" + cNmUsuario + '\'' +
                ", cEmail='" + cEmail + '\'' +
                ", cApelido='" + cApelido + '\'' +
                ", cSenha='" + cSenha + '\'' +
                ", iOfensiva=" + iOfensiva +
                ", iVida=" + iVida +
                ", iCoin=" + iCoin +
                ", cFotoPerfil='" + cFotoPerfil + '\'' +
                '}';
    }
}
