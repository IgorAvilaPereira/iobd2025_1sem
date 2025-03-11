DROP DATABASE IF EXISTS spotify_limitado;

CREATE DATABASE spotify_limitado;

\c spotify_limitado;

CREATE TABLE usuario (
    id serial primary key,
    nome text not null,
    email text unique,
    senha text CHECK(LENGTH(senha) >= 8)
);
INSERT INTO usuario (nome, email, senha) VALUES 
('Igor', 'igor.pereira@riogrande.ifrs.edu.br', md5('123')),
('Joly', 'joly@joly.com', md5('321'));

CREATE TABLE artista (
    id serial primary key,
    nome text not null,
    nome_artistico character varying(60)
);
INSERT INTO artista (nome) VALUES
('AMADO BATISTA');

INSERT INTO artista(nome, nome_artistico) VALUES
('LARISSA', 'ANITTA'),
('FRANCISCO', 'TIRIRICA'),
('NIVALDO LIMA', 'GUSTTAVO LIMA');

CREATE TABLE album (
    id serial primary key,
    titulo text not null,
    data_lancamento date,
    artista_id integer references artista (id)
);
INSERT INTO album (titulo, artista_id) values
('MEU LUGAR', 2),
('BANG', 2),
('DANÇA DA RAPADURA', 3),
('O EMBAIXADOR', 4);

CREATE TABLE musica (
    id serial primary key,
    titulo text not null,
    duracao integer check (duracao > 0),
    album_id integer references album (id)    
);
INSERT INTO musica (titulo, duracao, album_id) values
('QUEM SABE', 120, 1),
('MENINA MÁ', 150, 1),
('BANG', 300, 2),
('FLORENTINA', 500, 3),
('TCHETCHE', 100, 4);

CREATE TABLE playlist(
    id serial primary key,
    nome text not null,
    data_hora timestamp default current_timestamp,
    usuario_id integer references usuario (id)
);
INSERT INTO playlist (nome, usuario_id) values
('minhas canções', 1),
('emo', 1),
('samba 90', 1),
('minhas músicas pop by joly', 2),
('rock 80', 2);

CREATE TABLE playlist_musica (
    playlist_id integer references playlist(id),
    musica_id integer references musica (id),
    primary key (playlist_id, musica_id)
);
INSERT INTO playlist_musica (playlist_id, musica_id) values
(1, 3),
(1, 5);



