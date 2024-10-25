package com.example.monetizemaisback.model.user;


import lombok.Data;

@Data
public class UpdatePassword {
    private String email;
    private String password;
}
