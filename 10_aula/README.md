```sql
DROP DATABASE IF EXISTS teste;

CREATE DATABASE teste;

\c teste;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Tabela base
CREATE TABLE pessoa (
    id UUID DEFAULT uuid_generate_v4(),
    nome TEXT NOT NULL,
    email TEXT UNIQUE NOT NULL
);


CREATE TABLE cliente (
    CONSTRAINT cliente_pkey PRIMARY KEY (id),
    data_cadastro DATE NOT NULL DEFAULT CURRENT_DATE
) INHERITS (pessoa);


CREATE TABLE funcionario (
    CONSTRAINT funcionario_pkey PRIMARY KEY (id),
    salario NUMERIC(10,2) NOT NULL
) INHERITS (pessoa);


-- Inserindo um cliente
INSERT INTO cliente (nome, email) VALUES ('João da Silva', 'joao@email.com');

-- Inserindo um funcionário
INSERT INTO funcionario (nome, email, salario) VALUES ('Maria Souza', 'maria@email.com', 4500.00);


-- Busca em pessoa e todas as especializações
SELECT * FROM pessoa;

-- Busca apenas na tabela base
SELECT * FROM ONLY pessoa;

-- Busca específica
SELECT * FROM cliente;
SELECT * FROM funcionario;

--Considerações sobre Herança no PostgreSQL
--Chaves primárias e constraints não são herdadas automaticamente.
--Índices devem ser definidos em cada tabela filha.
--Pode ser necessário controle na aplicação para evitar IDs duplicados ou regras inconsistentes.
```
&nbsp;
