# Monetize+ - Backend 

  

Este repositório contém o backend do projeto **Monetize+**, uma aplicação educacional interativa para ajudar crianças do ensino fundamental a desenvolver habilidades financeiras. Esta aplicação é desenvolvida em **Java** com o objetivo de fornecer uma API segura e escalável para integração com o frontend. 

  

## Índice 

  

- [Tecnologias Utilizadas](#tecnologias-utilizadas) 

- [Requisitos](#requisitos) 

- [Configuração do Ambiente](#configuração-do-ambiente) 

- [Execução do Projeto](#execução-do-projeto) 

- [Rotas da API](#rotas-da-api)  

--- 

  

### Tecnologias Utilizadas 

  

- **Java** - Linguagem de programação principal. 

- **Spring Boot** - Framework para criação de aplicações Java. 

- **Hibernate** - ORM para facilitar a integração com o banco de dados. 

- **MySQL** - Banco de dados relacional. 

- **MongoDB** - Banco de dados NoSQL para armazenamento flexível de dados. 

- **Swagger** - Documentação interativa da API. 

  

### Requisitos 

  

- **Java 17** ou superior 

- **Maven** 3.8 ou superior 

- **MySQL** 8.0 ou superior 

- **MongoDB** 4.4 ou superior 

- **Git** para controle de versão 

  

### Configuração do Ambiente 

  

1. **Clone o repositório:** 

  

2. **Instale as dependências:** 

  

   ```bash 

   mvn install 

   ``` 

  

### Execução do Projeto 

  

Para executar o backend do Monetize+ em modo de desenvolvimento, utilize o seguinte comando: 

  

```bash 

mvn spring-boot:run 

``` 

Então, a API estará disponível em `http://localhost:8080`. 

  

### Rotas da API 

  

#### **ConteudoController** 

  

| Método | Rota                   | Descrição                     | 

|--------|-------------------------|-------------------------------| 

| GET    | `/getAllConteudos`      | Retorna todos os conteúdos    | 

| GET    | `/getConteudo/{id}`     | Retorna o conteúdo por ID     | 

  

#### **PerguntaController** 

  

| Método | Rota                    | Descrição                                         | 

|--------|--------------------------|---------------------------------------------------| 

| GET    | `/getPergunta/{progresso}` | Retorna a pergunta com base no progresso         | 

  

#### **UsuarioController** 

  

| Método  | Rota                               | Descrição                                 | 

|---------|------------------------------------|-------------------------------------------| 

| POST    | `/newUser`                         | Cria um novo usuário                      | 

| POST    | `/criarPergutnaUsuarioRepository`  | Salva uma nova pergunta para o usuário    | 

| GET     | `/getAllUsers`                     | Retorna todos os usuários                 | 

| GET     | `/getUser/{id}`                    | Retorna o usuário por ID                  | 

| GET     | `/getUserByEmail/{email}`          | Retorna o usuário pelo email              | 

| POST    | `/userLogin`                       | Realiza o login do usuário                | 

| GET     | `/getAllUsersByPoints`             | Retorna usuários ordenados por pontos     | 

| PUT     | `/updatePassword`                  | Atualiza a senha do usuário               | 

| GET     | `/checkUserByEmail/{email}`        | Verifica a existência de um usuário por email | 

| GET     | `/findUserByEmail/{email}`         | Busca um usuário pelo email               | 

| PUT     | `/updateStats/{email}`             | Atualiza as estatísticas do usuário       | 

| PUT     | `/updateProfilePicture`            | Atualiza a foto de perfil do usuário      | 

| PUT     | `/updateEmailApelido/{id}`         | Atualiza email e apelido do usuário       | 

| PUT     | `/updateLife/{email}/{life}`       | Atualiza a vida do usuário                | 

| PUT     | `/updateCoin/{email}/{coin}`       | Atualiza as moedas do usuário             | 

| PUT     | `/updateProgresso/{email}/{progresso}` | Atualiza o progresso do usuário e, opcionalmente, as moedas | 

| PUT     | `/updatePontos/{email}/{pontos}`   | Atualiza os pontos do usuário             | 

| PUT     | `/updateEmail/{email}/{newEmail}`  | Atualiza o email do usuário               | 

| PUT     | `/updateApelido/{email}/{apelido}` | Atualiza o apelido do usuário             | 

  

  

  

 
