// package com.example.monetizemaisback.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.servlet.config.annotation.CorsRegistry;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @Configuration
// public class WebConfig implements WebMvcConfigurer {

//     @Override
//     public void addCorsMappings(CorsRegistry registry) {
//         registry.addMapping("/**") // Permite CORS para todas as rotas
//                 .allowedOrigins("*") // Permite todas as origens (use com cuidado em produção)
//                 .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Permite os métodos especificados
//                 .allowedHeaders("*") // Permite todos os cabeçalhos
//                 .allowCredentials(true); // Permite cookies e credenciais (opcional)
//     }
// }
