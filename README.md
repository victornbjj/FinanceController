# FinanceController

API REST para controle de finanças pessoais, desenvolvida com Spring Boot 3 e Java 21.

## Tecnologias

- Java 21
- Spring Boot 3.5
- Spring Data JPA
- PostgreSQL (produção) / H2 (testes)
- Lombok
- Bean Validation
- Springdoc OpenAPI (Swagger)
- Docker & Docker Compose

## Funcionalidades

- Cadastro de transações financeiras (receitas e despesas)
- Listagem e busca de transações por ID
- Atualização e exclusão de transações
- Resumo financeiro com total de receitas, despesas e saldo
- Documentação interativa via Swagger UI

## Endpoints

| Método | Rota | Descrição |
|--------|------|-----------|
| `POST` | `/api/transactions` | Criar transação |
| `GET` | `/api/transactions` | Listar todas |
| `GET` | `/api/transactions/{id}` | Buscar por ID |
| `PUT` | `/api/transactions/{id}` | Atualizar |
| `DELETE` | `/api/transactions/{id}` | Deletar |
| `GET` | `/api/transactions/summary` | Resumo financeiro |

### Exemplo de payload

```json
{
  "description": "Salário",
  "amount": 3000.00,
  "type": "INCOME",
  "category": "Trabalho",
  "date": "2026-04-25"
}
```

> `type` aceita `INCOME` (receita) ou `EXPENSE` (despesa).

## Como rodar localmente

### Pré-requisitos

- Docker e Docker Compose instalados

### Passos

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/FinanceController.git
   cd FinanceController
   ```

2. Crie um arquivo `.env` na raiz do projeto:
   ```
   DB_PASSWORD=sua_senha
   ```

3. Suba os containers:
   ```bash
   docker-compose up --build
   ```

4. Acesse a API em `http://localhost:8080`
5. Acesse o Swagger em `http://localhost:8080/swagger-ui/index.html`

## Variáveis de ambiente

| Variável | Descrição | Padrão |
|----------|-----------|--------|
| `DB_PASSWORD` | Senha do banco PostgreSQL | — (obrigatória) |
| `DB_USERNAME` | Usuário do banco | `postgres` |
| `DB_URL` | URL JDBC do banco | `jdbc:postgresql://postgres_db:5432/financecontroller` |
| `SPRING_PROFILES_ACTIVE` | Perfil ativo | `prod` |

## Testes

```bash
./mvnw test
```

Os testes utilizam banco H2 em memória — nenhuma configuração extra necessária.

## Deploy

A aplicação está configurada para deploy via Docker no [Render](https://render.com).
