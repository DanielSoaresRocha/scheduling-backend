# Scheduling API - Contatos

## Descrição

Esta é a API de gerenciamento de contatos para o sistema Scheduling. O sistema permite a criação, leitura, atualização e remoção de contatos.

## Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- Banco de Dados Relacional PostgreSQL

## Endpoints Disponíveis

### Listar Todos os Contatos

**GET** `/contatos`

**Resposta:**

```json
[
  {
    "id": 1,
    "nome": "João Silva",
    "email": "joao@email.com",
    "telefone": "99999-9999"
  }
]
```

---

### Buscar Contato por ID

**GET** `/contatos/{id}`

**Resposta:**

```json
{
  "id": 1,
  "nome": "João Silva",
  "email": "joao@email.com",
  "telefone": "99999-9999"
}
```

Caso não seja encontrado, retorna **404 Not Found**.

---

### Criar um Novo Contato

**POST** `/contatos`

**Requisição:**

```json
{
  "nome": "Maria Souza",
  "email": "maria@email.com",
  "telefone": "98888-8888"
}
```

**Resposta:**

```json
{
  "id": 2,
  "nome": "Maria Souza",
  "email": "maria@email.com",
  "telefone": "98888-8888"
}
```

Caso haja erro de validação, retorna **400 Bad Request**.

---

### Atualizar Contato

**PUT** `/contatos/{id}`

**Requisição:**

```json
{
  "nome": "Maria Souza",
  "email": "maria.nova@email.com",
  "telefone": "97777-7777"
}
```

**Resposta:**

```json
{
  "id": 2,
  "nome": "Maria Souza",
  "email": "maria.nova@email.com",
  "telefone": "97777-7777"
}
```

Caso o contato não seja encontrado, retorna **404 Not Found**.

---

### Deletar Contato

**DELETE** `/contatos/{id}`

**Resposta:** **204 No Content**

Caso o contato não seja encontrado, retorna **404 Not Found**.

---

### Filtrar Contatos

**GET** `/contatos/filtrar`

Aceita filtros como nome, email e telefone via query parameters.

**Exemplo:** `/contatos/filtrar?nome=Maria&email=maria@email.com`

**Resposta:**

```json
[
  {
    "id": 2,
    "nome": "Maria Souza",
    "email": "maria@email.com",
    "telefone": "98888-8888"
  }
]
```

## Como Rodar o Projeto

1. Clone este repositório
2. Configure o banco de dados no `application.properties`
3. Rode a aplicação com `mvn spring-boot:run` ou pelo seu IDE favorito
4. A API estará disponível em `http://localhost:8080/contatos`

## License 📝

This project is licensed under the [MIT License](https://opensource.org/licenses/MIT) - see the [LICENSE](LICENSE) file for details.

## Autor

<table>
  <tr>
    <td align="center"><a href="https://github.com/DanielSoaresRocha"><img src="https://avatars0.githubusercontent.com/u/43214747?s=400&u=a267d113c5469b84bf87d202cdb7129330e4c865&v=4" width="100px;" alt="Daniel Soares"/><br /><sub><b>Daniel Soares</b></sub></a><br /><a href="https://github.com/DanielSoaresRocha/ESIG-challenge/commits?author=DanielSoaresRocha" title="Code">💻</a></td>
  <tr>
</table>
