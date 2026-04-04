# 🎮 Games List API - Backend

Uma API REST robusta e escalável para gerenciamento de listas de jogos, desenvolvida com **Spring Boot 21**, **Spring Security com JWT**, **PostgreSQL** e boas práticas de arquitetura de software.

Permite que usuários autenticados criem, organizem, busquem e reordenem coleções de jogos em diferentes categorias, com suporte a operações transacionais e projeções otimizadas.

---

## 🎯 Visão Geral

Este projeto implementa uma **API REST profissional** que consolida conceitos fundamentais e avançados do ecossistema Spring:

- ✅ Arquitetura em camadas (Controller → Service → Repository)
- ✅ Autenticação e autorização com JWT
- ✅ Banco de dados PostgreSQL com Hibernate
- ✅ Transações e operações ACID
- ✅ Projeções e DTOs para otimização
- ✅ CORS configurado
- ✅ Tratamento de exceções globalizado
- ✅ Relacionamentos complexos entre entidades
- ✅ Consultas personalizadas com JPQL
- ✅ Testes unitários com JUnit

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Versão | Motivo |
|-----------|--------|--------|
| **Java** | 21 LTS | Última versão LTS com suporte estendido até 2031 |
| **Spring Boot** | 3.2.0 | Framework web robusto com suporte completo a Java 21 |
| **Spring Security** | 6.x | Autenticação e autorização enterprise |
| **Spring Data JPA** | 3.x | Abstração de persistência eficiente |
| **Hibernate** | 6.3+ | ORM com suporte a Jakarta EE |
| **PostgreSQL** | 12+ | Banco relacional robusto e escalável |
| **JWT (JJWT)** | 0.12.3 | Autenticação stateless com tokens seguros |
| **Lombok** | 1.18.32 | Redução de boilerplate de código |
| **Maven** | 3.9.6 | Gerenciamento de dependências e build |

---

## 📊 Estrutura de Dados

### Entidades Principais

#### **Game** 🎮
Representa um jogo individual com seus atributos.
```
Game
├── id: Long (PK)
├── title: String (UNIQUE, NOT NULL)
├── score: Float
├── gameYear: Integer
├── genre: String
├── platforms: String
├── imgUrl: String (URL da imagem)
├── shortDescription: String
└── longDescription: String
```

#### **GameList** 📋
Categorias ou coleções de jogos (ex: "Top 10", "Favoritos").
```
GameList
├── id: Long (PK)
├── name: String
└── belongings: List<Belonging> (relacionamento)
```

#### **User** 👤
Usuários do sistema com autenticação JWT.
```
User
├── id: String (UUID, PK)
├── userName: String
├── email: String (UNIQUE)
├── password: String (BCrypt hash)
└── role: Role (USER ou ADMIN)
```

#### **Belonging** 🔗
Relacionamento muitos-para-muitos entre Game e GameList com ordenação.
```
Belonging (Tabela de junção)
├── gameId: Long (FK para Game)
├── listId: Long (FK para GameList)
└── position: Integer (ordem do jogo na lista)
```

### Diagrama de Relacionamentos

```
User (1) ------ (N) Autenticação (JWT)
   |
   |
GameList (1) -------- (N) Belonging (N) -------- (1) Game
   |                       |
   |                       └── position (ordem)
   └── ordem de games
```

---

## 🔐 Autenticação e Segurança

### JWT (JSON Web Token)

A API utiliza **JWT stateless** para autenticação:

1. **Login**: Envie credenciais (`username/email` + `password`)
2. **Token**: Receba um JWT com validade definida
3. **Acesso**: Inclua o token no header `Authorization: Bearer <token>`
4. **Refresh**: Utilize o refresh token para renovar a sessão

### Endpoints de Autenticação

```http
POST /users/login
Content-Type: application/json

{
  "email": "user@example.com",
  "password": "senha123"
}

Response 200:
{
  "accessToken": "eyJhbGciOiJIUzI1NiIs...",
  "refreshToken": "eyJhbGciOiJIUzI1NiIs...",
  "type": "Bearer"
}
```

### Usuários Padrão (Dados Iniciais)

| Usuário | Email | Role |
|---------|-------|------|
| user1 | user@example.com | USER |
| admin1 | admin@example.com | ADMIN |

⚠️ **Segurança**: Todas as senhas são armazenadas com BCrypt. Nunca commit de senhas em plain text.

---

## 📋 Endpoints da API

### Jogos (Games) 🎮

#### Listar todos os jogos
```http
GET /games
Response 200:
[
  {
    "id": 1,
    "title": "Mass Effect Trilogy",
    "score": 4.8,
    "year": 2012,
    "imgUrl": "...",
    "shortDescription": "Épica jornada pelo espaço"
  }
]
```

#### Buscar jogo por ID (requer autenticação)
```http
GET /games/{id}
Authorization: Bearer <token>

Response 200:
{
  "id": 1,
  "title": "Mass Effect Trilogy",
  "score": 4.8,
  "year": 2012,
  "genre": "Role-playing (RPG), Shooter",
  "longDescription": "..."
}
```

#### Buscar jogos por título (requer autenticação)
```http
GET /games/title?title=mass
Authorization: Bearer <token>

Response 200:
[
  {
    "id": 1,
    "title": "Mass Effect Trilogy",
    "score": 4.8
  }
]
```

#### Criar novo jogo (requer autenticação)
```http
POST /games
Authorization: Bearer <token>
Content-Type: application/json

{
  "title": "Novo Jogo",
  "score": 4.5,
  "gameYear": 2024,
  "genre": "RPG",
  "platforms": "PC, Switch",
  "imgUrl": "https://...",
  "shortDescription": "...",
  "longDescription": "..."
}

Response 201:
{
  "id": 7,
  "title": "Novo Jogo",
  ...
}
```

### Listas de Jogos (Game Lists) 📋

#### Listar todas as listas
```http
GET /lists
Response 200:
[
  { "id": 1, "name": "Top 10 RPGs" },
  { "id": 2, "name": "Favoritos" }
]
```

#### Listar jogos de uma lista específica
```http
GET /lists/{listId}/games
Response 200:
[
  {
    "id": 1,
    "position": 0,
    "title": "Mass Effect Trilogy",
    "score": 4.8
  }
]
```

#### Reordenar jogo dentro de uma lista (requer autenticação)
```http
POST /lists/{listId}/replacement
Authorization: Bearer <token>
Content-Type: application/json

{
  "sourceIndex": 0,
  "destinationIndex": 2
}

Response 204: No Content
```

### Usuários (Users) 👤

#### Registrar novo usuário
```http
POST /users
Content-Type: application/json

{
  "userName": "newuser",
  "email": "novo@example.com",
  "password": "senha123"
}

Response 201:
{
  "id": "uuid-...",
  "userName": "newuser",
  "email": "novo@example.com",
  "role": "USER"
}
```

#### Fazer login
```http
POST /users/login
Content-Type: application/json

{
  "email": "user@example.com",
  "password": "senha123"
}

Response 200:
{
  "accessToken": "...",
  "refreshToken": "...",
  "type": "Bearer"
}
```

#### Renovar token JWT
```http
POST /users/refresh
Content-Type: application/json

{
  "refreshToken": "..."
}

Response 200:
{
  "accessToken": "...",
  "refreshToken": "...",
  "type": "Bearer"
}
```

---

## 🚀 Como Executar

### Pré-requisitos

- ✅ Java 21+ instalado
- ✅ PostgreSQL 12+ rodando
- ✅ Maven 3.9.6+ instalado
- ✅ Git para clonar o repositório

### 1️⃣ Clonar o Repositório

```bash
git clone https://github.com/Lucasdelacerda/gamesList-backend.git
cd gamesList-backend
```

### 2️⃣ Configurar PostgreSQL

#### Criar banco de dados

```bash
psql -U postgres

postgres=# CREATE DATABASE dslist;
postgres=# \c dslist
```

#### Ou usando Docker

```bash
docker run --name postgres-games \
  -e POSTGRES_PASSWORD=postgres \
  -e POSTGRES_DB=dslist \
  -p 5432:5432 \
  -d postgres:15-alpine
```

### 3️⃣ Configurar Variáveis de Ambiente

Crie um arquivo `application-prod.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/dslist
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.format_sql=true
```

### 4️⃣ Executar a Aplicação

#### Development
```bash
./mvnw spring-boot:run
```

#### Production (PostgreSQL)
```bash
./mvnw spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=prod"
```

#### Via JAR
```bash
./mvnw clean package
java -jar target/gameList-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
```

### 5️⃣ Verificar se Está Rodando

```bash
curl http://localhost:8080/games
```

A API estará disponível em: **`http://localhost:8080`**

---

## 🗄️ Migração de H2 para PostgreSQL

Se estava usando H2 (desenvolvimento) e quer migrar para PostgreSQL (produção):

### 1. Parar a aplicação
### 2. Atualizar `application.properties`:

```properties
# De:
spring.datasource.url=jdbc:h2:file:./data/db
spring.datasource.driverClassName=org.h2.Driver
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# Para:
spring.datasource.url=jdbc:postgresql://localhost:5432/dslist
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

### 3. DDL PostgreSQL será criado automaticamente

Com `spring.jpa.hibernate.ddl-auto=update`, o schema será criado automaticamente.

---

## 🧪 Testes

### Executar testes unitários

```bash
./mvnw test
```

### Resultado esperado

```
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

---

## 📁 Estrutura do Projeto

```
gamesList-backend/
├── src/
│   ├── main/
│   │   ├── java/com/scrimet/dslist/
│   │   │   ├── DslistApplication.java
│   │   │   ├── config/
│   │   │   │   ├── CorsConfig.java
│   │   │   │   ├── SecurityConfig.java
│   │   │   │   └── JwtAuthenticationFilter.java
│   │   │   ├── controllers/
│   │   │   │   ├── GameController.java
│   │   │   │   ├── GameListController.java
│   │   │   │   ├── UserController.java
│   │   │   │   └── GlobalExceptionHandler.java
│   │   │   ├── dto/
│   │   │   │   ├── GameDTO.java
│   │   │   │   ├── GameMinDTO.java
│   │   │   │   ├── GameListDTO.java
│   │   │   │   ├── LoginRequestDTO.java
│   │   │   │   ├── LoginResponseDTO.java
│   │   │   │   ├── RefreshTokenRequestDTO.java
│   │   │   │   ├── UserDTO.java
│   │   │   │   └── ErrorResponseDTO.java
│   │   │   ├── entities/
│   │   │   │   ├── Game.java
│   │   │   │   ├── GameList.java
│   │   │   │   ├── User.java
│   │   │   │   ├── Role.java
│   │   │   │   ├── Belonging.java
│   │   │   │   └── BelongingPK.java
│   │   │   ├── exceptions/
│   │   │   │   ├── GameNotFoundException.java
│   │   │   │   └── ResourceNotFoundException.java
│   │   │   ├── projections/
│   │   │   │   ├── GameMinProjection.java
│   │   │   │   └── UserPanelProjection.java
│   │   │   ├── repositories/
│   │   │   │   ├── GameRepository.java
│   │   │   │   ├── GameListRepository.java
│   │   │   │   └── UserRepository.java
│   │   │   ├── services/
│   │   │   │   ├── GameService.java
│   │   │   │   ├── GameListService.java
│   │   │   │   ├── UsersService.java
│   │   │   │   └── CustomUserDetailsService.java
│   │   │   └── utils/
│   │   │       └── TokenProvider.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── application-prod.properties
│   │       └── import.sql
│   └── test/
│       └── java/.../DslistApplicationTests.java
├── pom.xml
├── mvnw
├── README.md
└── LICENSE
```

---

## 🛡️ Segurança

### Boas Práticas Implementadas

- ✅ **JWT**: Tokens seguros e stateless
- ✅ **BCrypt**: Senhas hasheadas com salt
- ✅ **CORS**: Configurado para origens específicas
- ✅ **CSRF**: Desabilitado para APIs REST (correto)
- ✅ **Autorização**: `@PreAuthorize` em endpoints sensíveis
- ✅ **Roles**: USER e ADMIN para controle granular

---

## 🚨 Tratamento de Erros

### Respostas Padronizadas

```json
{
  "error": "Not Found",
  "message": "Game with id 999 not found",
  "status": 404,
  "timestamp": "2024-04-04T13:00:00Z"
}
```

---

## 📈 Roadmap Futuro

- [ ] Paginação de resultados
- [ ] WebSocket para notificações real-time
- [ ] Cache distribuído com Redis
- [ ] Autenticação OAuth2 (Google, GitHub)
- [ ] Documentação OpenAPI/Swagger
- [ ] Testes de integração
- [ ] Deploy em Docker Compose
- [ ] CI/CD com GitHub Actions

---

## 🤝 Contribuindo

Contribuições são bem-vindas! Para contribuir:

1. Fork o repositório
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

---

## 📄 Licença

Este projeto está licenciado sob a MIT License - veja o arquivo [LICENSE](LICENSE) para detalhes.

---

## 👨‍💻 Autor

**Lucas de Lacerda**
- 🔗 [GitHub](https://github.com/Lucasdelacerda)
- 💼 [LinkedIn](https://linkedin.com/in/lucas-de-lacerda)
- 📧 Desenvolvedor Java | Spring Boot | Backend Engineer

---

## ⭐ Dê uma Estrela

Se este projeto foi útil, por favor considere dar uma ⭐ no GitHub!

---

**Última atualização**: Abril 2026  
**Versão**: 1.0.0  
**Status**: ✅ Em Produção com Java 21 LTS + PostgreSQL
