DROP DATABASE IF EXISTS aula2705;

CREATE DATABASE aula2705;

\c aula2705;

create table anotacao (
    id serial primary key,
    data_hora timestamp default current_timestamp,
    conteudo jsonb
);

INSERT INTO anotacao (conteudo) VALUES 
('{"titulo": "semana academica", "texto": "jovana palestrante", "cor": "pink"}');

INSERT INTO anotacao (conteudo) VALUES 
('{"titulo": "igor feliz", "texto": "conseguiu palestrantes para a semana academica", "cor": "yellow"}');

-- trocar  amarelo por azul na minha anotacao
 update anotacao set conteudo = jsonb_set(conteudo, '{cor}', '"blue"') where id = 3;


