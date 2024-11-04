package com.example.monetizemaisback.request;


import lombok.Data;

@Data
public class UpdatePassword {
    private String email;
    private String password;
}
