DROP DATABASE IF EXISTS chuva;

CREATE DATABASE chuva;

\c chuva;

CREATE TABLE pessoa (
    id serial primary key,
    nome text not null
);

INSERT INTO pessoa (nome) VALUES 
('IGOR'),
('JOÃO'),
('MARIA'),
('MÁRCIO');

CREATE TABLE dependente (
    id serial primary key,
    nome character varying(100) not null,
    pessoa_id integer references pessoa (id)
);

INSERT INTO dependente (nome, pessoa_id) VALUES
('IGOR JR.', 1),
('FULANO', 2),
('BELTRANO', 2),
('BETITO', 1);

INSERT INTO dependente (nome) values
('ORFÃO');

CREATE TABLE profissao (
    id serial primary key,
    descricao text not null
);
INSERT INTO profissao (descricao) values
('medico'),
('advogado'),
('professor');

CREATE TABLE pessoa_profissao (
    pessoa_id integer references pessoa (id),
    profissao_id integer references profissao (id),
    primary key (pessoa_id, profissao_id)
);
INSERT INTO pessoa_profissao (pessoa_id, profissao_id) VALUES
(1, 3),
(4, 3),
(4, 2),
(2, 1);

-- somente pessoas que tem filhos
-- SELECT pessoa.nome, dependente.nome FROM pessoa inner join dependente on (pessoa.id = dependente.pessoa_id) order by pessoa.nome;

-- pessoas que tem e que n tem filhos
--SELECT pessoa.nome, dependente.nome FROM pessoa left join dependente on (pessoa.id = dependente.pessoa_id) order by pessoa.nome;

-- pessoas que tem filhos + orfaos
--SELECT pessoa.nome, dependente.nome FROM pessoa right join dependente on (pessoa.id = dependente.pessoa_id) order by pessoa.nome;

-- tudo
-- SELECT pessoa.nome, dependente.nome FROM pessoa full join dependente on (pessoa.id = dependente.pessoa_id) order by pessoa.nome;

--SELECT pessoa.nome, profissao.descricao FROM pessoa INNER JOIN pessoa_profissao ON (pessoa.id = pessoa_profissao.pessoa_id) INNER JOIN profissao ON (profissao.id = pessoa_profissao.profissao_id);


--Select pessoa.nome FROM pessoa left join pessoa_profissao on (pessoa.id = pessoa_profissao.pessoa_id) where pessoa_profissao.pessoa_id is null;

--select pessoa.nome from pessoa where pessoa.id not in (select pessoa_id from pessoa_profissao);

-- SELECT pessoa.id, pessoa.nome, count(dependente.id) as qtde FROM pessoa left JOIN dependente on (pessoa.id = dependente.pessoa_id) group by pessoa.id;

--SELECT pessoa.id, pessoa.nome, count(dependente.id) as qtde FROM pessoa left JOIN dependente on (pessoa.id = dependente.pessoa_id) group by pessoa.id having count(dependente.id) >= 2;

--SELECT pessoa.id, pessoa.nome, count(pessoa_id) from pessoa inner join pessoa_profissao on (pessoa.id = pessoa_profissao.pessoa_id) group by pessoa.id;

ALTER TABLE pessoa_profissao ADD COLUMN data_inicio date;
ALTER TABLE pessoa_profissao ADD COLUMN data_fim date;
UPDATE pessoa_profissao SET data_inicio = '2014-09-11' where pessoa_id = 1;
ALTER TABLE pessoa_profissao ADD COLUMN salario money;
ALTER TABLE pessoa ADD COLUMN data_nascimento DATE;



