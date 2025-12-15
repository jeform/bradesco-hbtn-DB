-- SQL Schema for database_admin.db

-- Table: pessoas
CREATE TABLE pessoas (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL,
    email TEXT NOT NULL,
    idade INTEGER,
    cpf TEXT UNIQUE NOT NULL,
    data_nascimento DATE
);

-- Table: produtos
CREATE TABLE produtos (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL,
    quantidade INTEGER,
    preco REAL,
    status INTEGER
);
