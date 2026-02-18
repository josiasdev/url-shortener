# Encurtador de URLs (URL Shortener)

Este projeto é uma API REST desenvolvida para o desafio de encurtamento de URLs. O serviço permite transformar URLs longas em links compactos, facilitando o compartilhamento e gerenciando a expiração dos links.

## Tecnologias:
O projeto foi construído utilizando as seguintes tecnologias:

- Java 21
- Spring Boot 3
- Spring Data MongoDB (Persistência de dados)
- MongoDB (Banco de dados NoSQL)
- Docker & Docker Compose (Containerização)

## Funcionalidades:
[x] Encurtamento: Gera um código aleatório de 5 a 10 caracteres (letras e números).

[x] Redirecionamento: Redireciona o usuário da URL curta para a URL original.

[x] Validade: As URLs possuem prazo de expiração configurável.

[x] Tratamento de Erros: Retorno de 404 Not Found para URLs expiradas ou inexistentes.

## Como Rodar o Projeto
Pré-requisitos:
- Docker e Docker Compose instalados.
- Java 21 (caso queira rodar a aplicação fora do Docker).

### Passo a Passo
1. Clonar o repositório:
```
git clone https://github.com/josiasdev/url-shortener
cd url-shortener
```
2. Subir o banco de dados (MongoDB): <br>
O projeto já conta com um arquivo docker-compose.yml dentro da pasta /docker. Para subir o container:

```
cd docker
docker compose up -d
```

3. Configurar a Aplicação <br>
Certifique-se de que o arquivo src/main/resources/application.properties está configurado para conectar ao container local:
```
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=shortenerdb
spring.data.mongodb.username=admin
spring.data.mongodb.password=123
spring.data.mongodb.authentication-database=admin
```

4. Executar a API <br>
Volte para a raiz do projeto e execute o comando:

```
# Se estiver no Linux/macOS
./mvnw spring-boot:run

# Se estiver no Windows
mvnw.cmd spring-boot:run
```
A API estará disponível em http://localhost:8080

## Documentação da API
1. Encurtar URL <br>
POST /shorten-url

Corpo da Requisição:
```json
{
  "url": "https://backendbrasil.com.br"
}
```

Resposta (200 OK):
```json
{
  "url": "http://localhost:8080/DXB6V"
}
```

2. Redirecionar <br>
   GET /{shortCode}

- Sucesso: Redireciona para a URL original (302 Found).
- Erro: Retorna 404 caso o código não exista ou tenha expirado.