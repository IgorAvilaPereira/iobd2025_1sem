DROP DATABASE IF EXISTS restaurante;

CREATE DATABASE restaurante;

\c restaurante;

CREATE TABLE cliente (
    id serial primary key,
    email character varying(150) not null,
    nome character varying(200) not null,
    unique (email)
);

CREATE TABLE pedido (
    id serial primary key,
    data_hora timestamp default current_timestamp,
    cliente_id integer references cliente (id) ON DELETE CASCADE
);

CREATE TABLE produto (
    id serial primary key,
    nome text not null,
    preco numeric(10,2) default 0,
    check(preco >= 0)
    -- estoque
);

create table item_pedido (
    id serial PRIMARY key,
    pedido_id integer references pedido (id),
    produto_id integer REFERENCES produto (id), 
    quantidade integer,
    unique (pedido_id, produto_id)
);


CREATE USER usuario_app WITH PASSWORD 'senha123';

-- Permissões nas tabelas
GRANT CONNECT ON DATABASE restaurante TO usuario_app;
GRANT USAGE ON SCHEMA public TO usuario_app;
GRANT SELECT, INSERT, UPDATE ON cliente, produto, pedido, item_pedido TO usuario_app;

-- Permissões nas sequências
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO usuario_app;