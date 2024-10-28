package com.example.monetizemaisback.request;

import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Data
public class UpdateEmailApelidoRequest {

    @NotBlank
    private String apelido;

    @NotBlank
    @Email
    private String email;
}
