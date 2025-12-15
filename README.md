# Bradesco HBTN - Database Project

## Descrição

Projeto de gerenciamento de banco de dados utilizando JPA/Hibernate.

## Estrutura do Projeto

```
jpa_hibernate/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── demo/
│   │   │   │   └── AdministrativoApp.java
│   │   │   ├── entities/
│   │   │   │   ├── Pessoa.java
│   │   │   │   └── Produto.java
│   │   │   └── models/
│   │   │       ├── PessoaModel.java
│   │   │       └── ProdutoModel.java
│   │   └── resources/
│   │       └── META-INF/
│   │           └── persistence.xml
├── pom.xml
└── sql_schema_database_admin.sql
```

## Tecnologias

- Java
- JPA (Java Persistence API)
- Hibernate
- Maven

## Configuração

1. Clone o repositório
2. Configure o banco de dados no arquivo `persistence.xml`
3. Execute o schema SQL: `sql_schema_database_admin.sql`
4. Compile o projeto com Maven: `mvn clean install`

## Execução

```bash
cd jpa_hibernate
mvn exec:java -Dexec.mainClass="demo.AdministrativoApp"
```

## Autor

Jesse Machado

## Licença

Este projeto é parte do programa Bradesco/Holberton.
