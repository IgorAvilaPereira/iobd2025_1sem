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
