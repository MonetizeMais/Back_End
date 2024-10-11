package com.example.monetizemaisback.request;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoginRequest {

    private Long ncdUsuario;
    private String email;
    private String senha;
}
