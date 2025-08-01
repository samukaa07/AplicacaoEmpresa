# Sistema de Gerenciamento de Funcionários e Departamentos

## Descrição

Sistema backend desenvolvido em **Java 17** com **Spring Boot**, **JPA/Hibernate** e **MySQL**, que permite o gerenciamento completo de **funcionários** e **departamentos**, com suporte a:

- Cadastro, atualização e remoção de funcionários e departamentos
- Associação/desassociação entre funcionários e departamentos (relacionamento muitos-para-muitos)
- Consultas por nome, função, departamento e salário
- Testes simples via Postman

---

## Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Postman (testes de API REST)

---

## Estrutura do Banco de Dados

- **Banco de dados:** `avaliacao_db`
- **Tabelas:**
    - `funcionario`
    - `departamento`
    - `funcionario_departamento` (associação N:N)

> Os IDs são gerados como `UUID` e armazenados como `VARCHAR(36)`.

---

## Configurações - `application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/avaliacao_db
spring.datasource.username=root
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

---

## Endpoints REST

### Funcionário

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET    | `/funcionarios` | Lista todos os funcionários |
| GET    | `/funcionarios/{id}` | Buscar funcionário por ID |
| GET    | `/funcionarios?nome=Pedro` | Buscar por nome |
| GET    | `/funcionarios?nome=Pedro&funcao=Analista` | Buscar por nome e função |
| GET    | `/funcionarios?nome=Pedro&departamento=TI` | Buscar por nome e departamento |
| GET    | `/funcionarios/salario-maior-que?valor=5000` | Funcionários com salário maior que |
| POST   | `/funcionarios` | Cadastrar novo funcionário |
| PUT    | `/funcionarios/{id}` | Atualizar funcionário |
| DELETE | `/funcionarios/{id}` | Excluir funcionário |
| POST   | `/funcionarios/{idFuncionario}/departamentos/{idDepartamento}` | Associar departamento |
| DELETE | `/funcionarios/{idFuncionario}/departamentos/{idDepartamento}` | Desassociar departamento |

### Departamento

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET    | `/departamentos` | Lista todos os departamentos |
| GET    | `/departamentos/{id}` | Buscar por ID |
| GET    | `/departamentos?departamento=RH` | Buscar por nome |
| POST   | `/departamentos` | Cadastrar departamento |
| PUT    | `/departamentos/{id}` | Atualizar departamento |
| DELETE | `/departamentos/{id}` | Excluir departamento |

---

## Exemplo de Teste via Postman

### 1. Criar Funcionário

```json
POST /funcionarios
{
  "nome": "João Silva",
  "endereco": "Rua A",
  "bairro": "Centro",
  "cep": "12345-678",
  "telefone": "11999999999",
  "salario": 4500.00,
  "dataContrato": "2025-07-31T22:47:40",
  "funcao": "Desenvolvedor"
}
```

### 2. Criar Departamento

```json
POST /departamentos
{
  "departamento": "TI",
  "qtdeFuncionarios": 5
}
```

### 3. Associar Funcionário a Departamento

```
POST /funcionarios/{idFuncionario}/departamentos/{idDepartamento}
```

### 4. Desassociar Funcionário de Departamento

```
DELETE /funcionarios/{idFuncionario}/departamentos/{idDepartamento}
```

---

## Autor

**Samuel Pereira Lima**
