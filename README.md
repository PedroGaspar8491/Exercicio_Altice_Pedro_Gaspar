# Labsec Calculator

Este projeto consiste numa **REST API** simples (Quarkus) com uma interface para o utilizador interagir (Angular). Estes componentes estão disponibilizados através de **containers Docker**.

## Requisitos

- Docker
- Docker Compose
- Maven (scripts necessários disponibilizados no repositório)
- Node.js

## Como Usar

1. Clone o repositório.

   ```bash
   git clone https://github.com/PedroGaspar8491/Exercicio_Altice_Pedro_Gaspar
   ```

2. Construa as aplicações:

   ```bash
   # A partir da pasta root da aplicação
   cd labsec-quarkus
   ./mvnw clean package
   cd ..

   cd labsec-angular
   npm install
   npm run build -- --configuration production
   cd ..
   ```

3. Execute os containers

   ```bash
   docker compose up --build
   ```

## URLs Relevantes

- Backend (Quarkus) : http://localhost:8080
- Frontend (Angular) : http://localhost:4200

## REST API

URL Base: `http://localhost:8080`

### Endpoint

- **GET: /labsec/{n}**

  - **Descrição:** Retorna o valor LabSec de um número inteiro e positivo (n);

  - **Parâmetros:**
    - `n`(integer, obrigatório)
  - **Respostas:**

    -`200 OK:` Retorna o valor LabSec de n como um String;

    -`400 Bad Request:` Parâmetro inválido, como um número negativo;

    -`500 Internal Server Error:` Erro inesperado;

### Documentação API

- Swagger UI: http://localhost:8080/swagger-ui

## Interface Gráfica

- Acessivel em: http://localhost:4200
- Características:
  - Input para o integer `n`;
  - Mostra o resultado obtido pelo endpoint;
  - Mostra mensagem de erro, caso estes aconteçam;
