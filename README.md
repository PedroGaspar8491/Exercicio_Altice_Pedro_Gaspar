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
   2.1 Erros comuns:
      - JAVA_HOME não está definida: Este erro apresenta-se ao utilizar o comando `./mvnw clean package` quando o utilizador não definiu a JAVA_HOME no PATH. Isto pode ser resolvido ao editar as variáveis de ambiente em sistemas Windows, ou exportando a localização do javac em sistemas UNIX.
      - Permission denied: ./mvnw: Em sistemas Unix, poderá ser necessário alterar as permissões do script mvnw. Isto pode ser feito através do comando `chmod +x ./mvnw`.

4. Execute os containers

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
