# 🎮 Game List API — Spring Boot

Projeto desenvolvido durante um **intensivão de Spring Boot**, com o objetivo de **aprofundar conhecimentos práticos em desenvolvimento back-end**, arquitetura REST, persistência de dados e boas práticas com Java.

A aplicação consiste em uma **API REST para gerenciamento de listas de jogos**, permitindo organizar, listar e reordenar jogos dentro de diferentes categorias.

---

## 🚀 Tecnologias Utilizadas

* **Java 21**
* **Spring Boot**
* **Spring Data JPA**
* **Hibernate**
* **Banco de Dados Relacional** (H2 / PostgreSQL)
* **Maven**
* **RESTful API**


---

## 🧠 Conceitos Aplicados

Este projeto foi pensado para consolidar conceitos fundamentais e avançados do ecossistema Spring:

* Arquitetura em camadas (Controller, Service, Repository)
* Mapeamento objeto-relacional (JPA / Hibernate)
* Relacionamentos entre entidades
* Consultas customizadas com JPQL
* Projeções e DTOs
* Ordenação e reordenação de listas
* Transações com `@Transactional`
* Boas práticas de design de API REST
* Separação de responsabilidades
* Versionamento de código com Git

---

## 📌 Funcionalidades

* Listar jogos cadastrados
* Listar categorias/listas de jogos
* Buscar jogos por lista
* Reordenar jogos dentro de uma lista
* API organizada e padronizada
* Pronta para consumo por aplicações front-end

---

## 🗂️ Estrutura do Projeto

```
src/main/java
 └── com.scrimet.dslist
     ├── controllers
     ├── dto
     ├── entities
     ├── repositories
     ├── services
     
```

A estrutura segue o padrão recomendado pelo Spring, facilitando manutenção, testes e escalabilidade.

---

## ⚙️ Como Executar o Projeto

### Pré-requisitos

* Java 21
* Maven
* IDE (IntelliJ, Eclipse ou VS Code)

### Passos

```bash
# Clonar 
# 
# o repositório
git clone https://github.com/Lucasdelacerda/gamesList-backend

# Entrar no projeto
cd gamesList-backend

# Executar a aplicação
./mvnw spring-boot:run
```

A API ficará disponível em:

```
http://localhost:8080
```

---

## 🧪 Testes

* Testes realizados utilizando **JUnit**
* Validação de regras de negócio e comportamento da API
* Estrutura preparada para expansão da cobertura de testes

---

## 🎯 Objetivo do Projeto

Este projeto faz parte do meu processo de **evolução técnica como desenvolvedor Java**, com foco em:

* Consolidar fundamentos do Spring Boot
* Escrever código limpo e organizado
* Trabalhar com banco de dados de forma eficiente
* Simular um cenário real de API back-end profissional

---

## 📢 Divulgação

Este projeto está sendo divulgado no **LinkedIn** como parte da minha jornada de aprendizado e evolução na área de desenvolvimento back-end com Java e Spring Boot.

Feedbacks são bem-vindos.

---

## 👨‍💻 Autor

**Lucas de Lacerda**
Desenvolvedor Java | Spring Boot
📍 Brasil

---

⭐ Se esse projeto te ajudou de alguma forma, deixe uma estrela no repositório.
