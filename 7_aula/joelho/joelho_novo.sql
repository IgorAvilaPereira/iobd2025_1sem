DROP DATABASE IF EXISTS joelho_novo;

CREATE DATABASE joelho_novo;

\c joelho_novo;

CREATE TABLE paciente (
    id serial primary key,
    nome text not null,
    cpf character(11) unique,
    local_dor text,
    nivel integer check (nivel >= 1 and nivel <= 10) 
);
INSERT INTO paciente (cpf, nome, local_dor, nivel) VALUES
('11111111111', 'IGOR PEREIRA', 'joelho', 3);

CREATE TABLE fisioterapeuta (
    id serial primary key,
    nome text not null,
    crefito character(5) UNIQUE
);
INSERT INTO fisioterapeuta (crefito, nome) VALUES
('11111', 'PAULO PAIXÃO');

CREATE TABLE sessao (
    id serial primary key,    
    fisioterapeuta_id integer references fisioterapeuta (id),
    paciente_id integer references paciente (id),
    data_hora timestamp default current_timestamp
);
INSERT INTO sessao (fisioterapeuta_id, paciente_id) VALUES
(1,1);


ALTER TABLE fisioterapeuta ADD COLUMN ativo boolean DEFAULT TRUE;
ALTER TABLE paciente ADD COLUMN ativo boolean DEFAULT TRUE;
ALTER TABLE paciente ADD COLUMN foto bytea;
ALTER TABLE fisioterapeuta ADD COLUMN foto bytea;
ALTER TABLE paciente ALTER COLUMN cpf SET NOT NULL;

