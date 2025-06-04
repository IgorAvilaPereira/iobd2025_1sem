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

--Adicione a coluna data_nascimento na tabela de usuários. Além disso, coloque uma cláusula CHECK permitindo somente anos de nascimento >= 1900
ALTER TABLE usuario ADD COLUMN data_nascimento 
DATE CHECK(EXTRACT(YEAR FROM data_nascimento) >= 1900);

--Retorne os nomes dos usuários e suas datas de nascimento formatadas em dia/mes/ano. Para testar será preciso inserir ou atualizar as datas de nascimento de alguns usuários
UPDATE usuario SET data_nascimento = '1987-01-20' where id = 1;
UPDATE usuario SET data_nascimento = '1991-09-13' where id = 2;

select nome, to_char(data_nascimento, 'DD/MM/YYYY') as data_formatada from usuario;

-- Delete usuários sem nome
-- jump

--Torne a coluna nome da tabela usuários obrigatória
-- feito!
ALTER TABLE usuario alter column nome set not null; 

--Retorne os títulos de todos os álbuns em maiúsculo
SELECT upper(titulo) from album;

SELECT lower(titulo) as titulo from album order by titulo;

--Retorne os títulos de todos os álbuns em maiúsculo
update album set data_lancamento = '2015-10-13' where id = 2;
SELECT upper(titulo) as titulo from album order by titulo;

--Retorne somente os títulos dos 2 primeiros álbuns cadastrados
SELECT upper(titulo) as titulo from album limit 2;

--Retorne o nome e o email de todos os usuários separados por ponto-e-vírgula
SELECT nome||';'||email as exportacao from usuario order by id;

--Retorne músicas com duração entre 100 e 200 segundos
SELECT musica.titulo, duracao, album.titulo FROM album INNER JOIN musica ON (album.id = musica.album_id) where duracao between 100 and 200 order by album.id;

-- off-topic: artistas e seus albuns
--SELECT coalesce(artista.nome_artistico, artista.nome), album.titulo from artista inner join album on (artista.id = album.artista_id);

-- Retorne, preferencialmente, o nome de todos os artistas. Caso um determinado artista não tenha cadastrado seu nome, retorne na mesma consulta seu nome artístico
-- opcao 1
SELECT coalesce(artista.nome_artistico, artista.nome), album.titulo from artista inner join album on (artista.id = album.artista_id);

-- opcao 2
SELECT 
    CASE
        when nome_artistico is not null then nome_artistico
        ELSE artista.nome
    END, album.titulo from artista inner join album on (artista.id = album.artista_id);
    
-- Retorne o título dos álbuns lançados em 2023
select titulo, data_lancamento from album where extract(year from data_lancamento) = 2023;


-- Retorne o nome das playlists que foram criadas hoje
SELECT usuario.nome,  playlist.nome from usuario inner join playlist on (usuario.id = playlist.usuario_id) where cast(data_hora as date) = current_date;

SELECT usuario.nome,  playlist.nome from usuario inner join playlist on (usuario.id = playlist.usuario_id) where data_hora::date = current_date;

-- Atualize todos os nomes dos artistas (nome e nome_artistico) para maiúsculo
 UPDATE artista SET nome_artistico = upper(nome_artistico), nome = upper(nome);

--Coloque uma verificação para a coluna duracao (tabela musica) para que toda duração tenha mais de 0 segundos

--ALTER TABLE musica ADD CONSTRAINT duracao integer check (duracao > 0);

-- Adicione uma restrição UNIQUE para a coluna email da tabela usuario
--ALTER TABLE usuario ADD unique(email);

-- Retorne somente os artistas que o nome artístico começa com "Leo" (Ex: Leo Santana, Leonardo e etc.)
SELECT * FROM artista WHERE nome_artistico ILIKE 'ANI%';


--- Retorne o título dos álbuns que estão fazendo aniversário neste mês
select * from album where extract(month from data_lancamento) = extract(month from current_date) order by titulo;

-- Retorne o título dos álbuns lançados no segundo semestre do ano passado (de julho de 2024 a dezembro de 2024)
select titulo from album where extract(month from data_lancamento) >= 7 and extract(year from data_lancamento) = EXTRACT(YEAR FROM CURRENT_DATE) - 1;

select titulo from album where extract(month from data_lancamento) >= 7 and extract(year from data_lancamento) = extract(year from CURRENT_DATE - interval '1 year');


--Retorne o título dos álbuns lançados nos últimos 30 dias
SELECT titulo from album where data_lancamento >= CURRENT_DATE - INTERVAL '30 days';

-- ou spotify_limitado=# select CURRENT_DATE - 30;

-- Retorne o título e o dia de lançamento (por extenso) de todos os álbuns
-- Retorne o título e o mês de lançamento (por extenso) de todos os álbuns
 SELECT titulo,     
    extract(day from data_lancamento) || ' de ' || 
    CASE
        when EXTRACT(MONTH FROM data_lancamento) = 1 then 'janeiro'
        when EXTRACT(MONTH FROM data_lancamento) = 2 then 'fevereiro'
        when EXTRACT(MONTH FROM data_lancamento) = 3 then 'março'
        when EXTRACT(MONTH FROM data_lancamento) = 4 then 'abril'
        when EXTRACT(MONTH FROM data_lancamento) = 5 then 'maio'
        when EXTRACT(MONTH FROM data_lancamento) = 6 then 'junho'
        when EXTRACT(MONTH FROM data_lancamento) = 7 then 'julho'
        when EXTRACT(MONTH FROM data_lancamento) = 8 then 'agosto'
        when EXTRACT(MONTH FROM data_lancamento) = 9 then 'setembro'
        when EXTRACT(MONTH FROM data_lancamento) = 10 then 'outubro'
        when EXTRACT(MONTH FROM data_lancamento) = 11 then 'novembro'
        when EXTRACT(MONTH FROM data_lancamento) = 12 then 'dezembro'
    END || ' de ' || extract(year from data_lancamento) ||  ' ' ||
    CASE
        when EXTRACT(DOW FROM data_lancamento) = 0 then 'domingo'
        when EXTRACT(DOW FROM data_lancamento) = 1 then 'segunda'
        when EXTRACT(DOW FROM data_lancamento) = 2 then 'terca'
        when EXTRACT(DOW FROM data_lancamento) = 3 then 'quarta'
        when EXTRACT(DOW FROM data_lancamento) = 4 then 'quinta'
        when EXTRACT(DOW FROM data_lancamento) = 5 then 'sexta'
        when EXTRACT(DOW FROM data_lancamento) = 6 then 'sabado'
    END as data_lancamento_por_extenso FROM album;


--  Retorne pelo menos um dos álbuns mais antigos
select titulo, data_lancamento from album order by data_lancamento DESC LIMIT 1;
select * from album inner join artista on (artista.id = album.artista_id) where data_lancamento = (select min(data_lancamento) from album);


-- Retorne pelo menos um dos álbuns mais recentes
-- jump invertido da anterior

-- Liste os títulos das músicas de todos os álbuns de um determinado artista
select musica.titulo, artista.nome, album.titulo from musica inner join album on (album.id = musica.album_id) inner join artista on (artista.id = album.artista_id);




