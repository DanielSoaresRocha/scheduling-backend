# Scheduling API - Contacts

## Description

This is the contact management API for the Scheduling system. The system allows the creation, reading, updating, and deletion of contacts.

## Technologies Used

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- Relational Database: PostgreSQL

## Available Endpoints

### List All Contacts

**GET** `/contacts`

**Response:**

```json
[
  {
    "id": 1,
    "name": "João Silva",
    "email": "joao@email.com",
    "phone": "99999-9999"
  }
]
```

---

### Get Contact by ID

**GET** `/contacts/{id}`

**Response:**

```json
{
  "id": 1,
  "name": "João Silva",
  "email": "joao@email.com",
  "phone": "99999-9999"
}
```

If not found, returns **404 Not Found**.

---

### Create a New Contact

**POST** `/contacts`

**Request:**

```json
{
  "name": "Maria Souza",
  "email": "maria@email.com",
  "phone": "98888-8888"
}
```

**Response:**

```json
{
  "id": 2,
  "name": "Maria Souza",
  "email": "maria@email.com",
  "phone": "98888-8888"
}
```

If there is a validation error, returns **400 Bad Request**.

---

### Update Contact

**PUT** `/contacts/{id}`

**Request:**

```json
{
  "name": "Maria Souza",
  "email": "maria.nova@email.com",
  "phone": "97777-7777"
}
```

**Response:**

```json
{
  "id": 2,
  "name": "Maria Souza",
  "email": "maria.nova@email.com",
  "phone": "97777-7777"
}
```

If the contact is not found, returns **404 Not Found**.

---

### Delete Contact

**DELETE** `/contacts/{id}`

**Response:** **204 No Content**

If the contact is not found, returns **404 Not Found**.

---

### Filter Contacts

**GET** `/contacts/filter`

Accepts filters such as name, email, and phone via query parameters.

**Example:** `/contacts/filter?name=Maria&email=maria@email.com`

**Response:**

```json
[
  {
    "id": 2,
    "name": "Maria Souza",
    "email": "maria@email.com",
    "phone": "98888-8888"
  }
]
```

## How to Run the Project

1. Clone this repository
2. Configure the database in `application.properties`
3. Run the application using `mvn spring-boot:run` or from your favorite IDE
4. The API will be available at `http://localhost:8080/contacts`

## License 📝

This project is licensed under the [MIT License](https://opensource.org/licenses/MIT) - see the [LICENSE](LICENSE) file for details.

## Author

<table>
  <tr>
    <td align="center"><a href="https://github.com/DanielSoaresRocha"><img src="https://avatars0.githubusercontent.com/u/43214747?s=400&u=a267d113c5469b84bf87d202cdb7129330e4c865&v=4" width="100px;" alt="Daniel Soares"/><br /><sub><b>Daniel Soares</b></sub></a><br /><a href="https://github.com/DanielSoaresRocha/ESIG-challenge/commits?author=DanielSoaresRocha" title="Code">💻</a></td>
  <tr>
</table>

