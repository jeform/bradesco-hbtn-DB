CREATE TABLE aluno (id  integer, email varchar(255), matricula varchar(255), nome varchar(255) not null, primary key (id), unique (matricula));
CREATE TABLE curso (id  integer, carga_horaria integer, descricao varchar(255), nome varchar(255) not null, material_curso_id bigint, professor_id bigint not null, primary key (id), unique (material_curso_id));
CREATE TABLE curso_aluno (curso_id bigint not null, aluno_id bigint not null);
CREATE TABLE endereco (id  integer, bairro varchar(255), cep varchar(255), cidade varchar(255), complemento varchar(255), estado varchar(255), logradouro varchar(255), numero varchar(255), aluno_id bigint, primary key (id));
CREATE TABLE material_curso (id  integer, descricao varchar(255), tipo varchar(255), titulo varchar(255) not null, url varchar(255), primary key (id));
CREATE TABLE professor (id  integer, email varchar(255), especialidade varchar(255), nome varchar(255) not null, primary key (id));
CREATE TABLE telefone (id  integer, numero varchar(255) not null, tipo varchar(255), aluno_id bigint, primary key (id));
