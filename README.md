# 🏠 Home Pocket — Backend

> API REST do Home Pocket, aplicativo de gestão doméstica compartilhada.

---

## 📋 Sobre

Este repositório contém o backend do **Home Pocket**, responsável por toda a lógica de negócio, autenticação, persistência de dados e comunicação em tempo real entre os membros de uma casa.

---

## 🛠️ Stack

| Tecnologia            | Uso |
|-----------------------|---|
| Java 21               | Linguagem principal |
| Spring Boot 3         | Framework principal |
| Spring Security + JWT | Autenticação e autorização |
| Spring Data JPA       | Persistência de dados |
| PostgreSQL            | Banco de dados relacional |
| WebSocket (STOMP)     | Comunicação em tempo real |
| Docker                | Containerização |

---

## 🏗️ Estrutura do Projeto

```
src/main/java/com/homepocket/
├── auth/           # Autenticação, JWT e registro de usuários
├── house/          # Módulo de casas e membros
├── market/         # Módulo de lista de mercado
├── event/          # Módulo de eventos
├── finance/        # Módulo de finanças
└── shared/         # Exceções, DTOs e utilitários comuns
```

---

## 🚀 Como Rodar

### Pré-requisitos
- Java 17+
- Docker e Docker Compose

### Passos

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/home-pocket-backend.git
cd home-pocket-backend

# Suba o banco de dados
docker-compose up -d

# Rode a aplicação
./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8080`.

---

## 🔐 Autenticação

A API utiliza **JWT Bearer Token**. Após o login, inclua o token no header de todas as requisições protegidas:

```
Authorization: Bearer <seu-token>
```

---

## 📡 Endpoints

### Auth
```
POST   /api/auth/register         → Cadastro de usuário
POST   /api/auth/login            → Login e geração de token
```

### Casas
```
POST   /api/houses                → Criar casa
POST   /api/houses/{id}/invite    → Convidar morador
GET    /api/houses/{id}/members   → Listar membros
```

### Lista de Mercado
```
GET    /api/houses/{id}/market         → Listar itens
POST   /api/houses/{id}/market         → Adicionar item
PATCH  /api/market/{itemId}            → Atualizar status ou responsável
DELETE /api/market/{itemId}            → Remover item
```

### Eventos
```
GET    /api/houses/{id}/events    → Listar eventos
POST   /api/houses/{id}/events    → Criar evento
PATCH  /api/events/{eventId}      → Atualizar evento
DELETE /api/events/{eventId}      → Remover evento
```

### Finanças
```
GET    /api/houses/{id}/finances  → Listar gastos
POST   /api/houses/{id}/finances  → Registrar gasto
DELETE /api/finances/{id}         → Remover registro
```

---

## 📌 Roadmap

- [ ] Autenticação com JWT
- [ ] Módulo de casas e convites
- [ ] Lista de mercado com tempo real (WebSocket)
- [ ] Módulo de eventos
- [ ] Módulo de finanças (fase 1)
- [ ] Notificações push
- [ ] Divisão de despesas entre moradores

---

## 👤 Autor

Desenvolvido por **[Seu Nome]** como projeto de portfólio.

[![LinkedIn](https://img.shields.io/badge/LinkedIn-blue?style=flat&logo=linkedin)](https://linkedin.com/in/seu-perfil)
[![GitHub](https://img.shields.io/badge/GitHub-black?style=flat&logo=github)](https://github.com/seu-usuario)

---

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.