DROP DATABASE IF EXISTS trabalho1;

CREATE DATABASE trabalho1;

\c trabalho1;

CREATE SCHEMA gerenciamento_conta;

-- quais sao os schemas possiveis para meu banco? o proprio public e o schema recem criado!
SET search_path TO public, gerenciamento_conta;

CREATE TABLE gerenciamento_conta.usuario (
    id serial primary key,
    nome character varying(100) not null,
    email character varying(100) not null,
    senha character varying(100) not null,
    data_nascimento date check(extract(year from data_nascimento) >= 1900),
    unique(email)   
);
INSERT INTO gerenciamento_conta.usuario (nome, email, senha, data_nascimento) VALUES 
('IGOR PEREIRA', 'igor.pereira@riogrande.ifrs.edu.br', md5('123'), '1987-01-20');

CREATE TABLE gerenciamento_conta.conta (
    id serial primary key,
    usuario_id integer references usuario (id),
    data_hora_criacao timestamp default current_timestamp,
    nome_usuario text not null unique
);
INSERT INTO gerenciamento_conta.conta (usuario_id, nome_usuario) VALUES 
(1, '@igoravilapereira'),
(1, '@igor_guitarhero');

CREATE TABLE publicacao (
    id serial primary key,
    data_hora timestamp default current_timestamp,
    texto text,
    arquivo_principal text not null,
    latitude real,
    longitude real
);

INSERT INTO publicacao (texto, arquivo_principal) VALUES
('Minhas férias no caribe', 'minha_foto_no_caribe.jpeg'),
('Aprendendo pentatônicas como guitar hero', 'video_licao1.avi'),
('modos gregos musicais - te cuida matheus asato!', 'video_modos_gregos.mkv');

CREATE TABLE conta_publicacao (
    publicacao_id integer references publicacao (id),
    conta_id integer REFERENCES gerenciamento_conta.conta (id),
    primary key (conta_id, publicacao_id)
);
INSERT INTO conta_publicacao (conta_id, publicacao_id) VALUES
(1,1),
(2,2),
(2,3);

CREATE TABLE comentario (
    id serial primary key,
    data_hora timestamp default current_timestamp,
    -- forma 1 de criar fk
    publicacao_id integer references publicacao (id),
    -- jeito 2 de criar fk
    conta_id integer,
    FOREIGN KEY (conta_id) REFERENCES gerenciamento_conta.conta (id)
);

CREATE VIEW qtde_conta_por_usuario AS SELECT usuario.id, usuario.nome, count(*) from usuario inner join conta on (usuario.id = conta.usuario_id) group by usuario.id, usuario.nome;

-- 1) SELECT usuario.nome, conta.nome_usuario FROM usuario inner join conta on (usuario.id = conta.usuario_id) ORDER BY usuario.nome;
-- 1.2) SELECT usuario.nome, STRING_AGG(conta.nome_usuario, ', ') as contas FROM usuario inner join conta on (usuario.id = conta.usuario_id) GROUP BY usuario.nome;
















