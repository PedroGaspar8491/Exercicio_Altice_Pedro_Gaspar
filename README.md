# Labsec Calculator

Este projeto consiste numa **REST API** simples (Quarkus) com uma interface para o utilizador interagir (Angular). Estes componentes estão disponibilizados através de **containers Docker**.

## Requisitos

- Docker
- Docker Compose

## Como Correr os Containers

Num terminal localizado na pasta root, temos que utilizar o seguinte comando:

```bash
docker compose up --build
```

Depois de um periodo de tempo, a aplicação estará disponível.

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
