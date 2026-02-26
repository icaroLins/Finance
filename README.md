# 💰 Sistema de Controle Financeiro — Backend

Backend de um **Sistema de Controle Financeiro** desenvolvido com **Spring Boot**, focado no gerenciamento de lançamentos financeiros por usuário, com organização por **mês e ano**, autenticação e boas práticas REST.

O projeto segue uma arquitetura em camadas, com foco em código limpo, segurança e escalabilidade, servindo como base para integração com aplicações frontend (web ou mobile).

---

## 🚀 Funcionalidades

- 👤 Cadastro e autenticação de usuários
- 🔐 Autenticação e autorização com JWT
- 💸 Criação de lançamentos financeiros (receitas e despesas)
- 🗂️ Associação de lançamentos a categorias
- 📅 Filtro de lançamentos por **mês e ano**
- 🔍 Listagem de dados por usuário autenticado
- 📄 Documentação automática da API com Swagger
- 🧹 Validações e tratamento de exceções
- 🧱 Arquitetura em camadas (Controller, Service, Repository)

---

## 🛠️ Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- Spring Security + JWT
- Hibernate
- swagger
- PostgreSQL
- Maven
- Git & GitHub

---

## 🏗️ Arquitetura do Projeto

O sistema segue o padrão **MVC adaptado para REST**, dividido em camadas bem definidas:


### Camadas
- **Controller**: expõe os endpoints REST e recebe as requisições HTTP
- **Service**: contém as regras de negócio
- **Repository**: acesso aos dados via JPA
- **Entity**: mapeamento das tabelas do banco de dados
- **Security**: autenticação, autorização e JWT

---

## 📅 Filtro por Mês e Ano

Os lançamentos financeiros são filtrados por intervalo de datas, garantindo melhor performance e uso de índices no banco de dados.


---

## 🔐 Segurança

- Autenticação baseada em JWT
- Cada usuário acessa apenas seus próprios dados
- Endpoints protegidos com Spring Security

---

## ⚙️ Configuração do Ambiente

### Pré-requisitos

- Java 17 ou superior
- Maven
- PostgreSQL
- Git

### Configuração do banco de dados

No arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/finance_db
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

````

###▶️ Como Executar o Projeto

````
# entrar no diretório do projeto
cd seu-repositorio

# executar a aplicação
mvn spring-boot:run
````
### 👨‍💻 Autor
Ícaro Lins

Desenvolvedor Backend Java

GitHub: https://github.com/icaroLins

Linkedin: https://www.linkedin.com/in/icaro-lins-gomes-16324422b/
