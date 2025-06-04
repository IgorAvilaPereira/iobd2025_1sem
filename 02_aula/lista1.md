# Lista 1 - SQL

* Insira no mínimo 3 tuplas em cada tabela <!--(Dica: INSERT INTO)-->
* Adicione a coluna _data_nascimento_ na tabela de _usuários_. Além disso, coloque uma cláusula CHECK permitindo somente anos de nascimento >= 1900
* Retorne os _nomes_ dos _usuários_ e suas _datas de nascimento_ formatadas em dia/mes/ano. Para testar será preciso inserir ou atualizar as datas de nascimento de alguns usuários<!--postgres=# select to_char(current_date, 'DD/MM/YYYY') as dma;-->
* Delete _usuários_ sem _nome_ <!--(Dica: DELETE)-->
* Torne a coluna _nome_ da tabela _usuários_ obrigatória <!--(Dica: NOT NULL)-->
* Retorne os _títulos_ de todos os _álbuns_ em maiúsculo <!--(Dica: UPPER)-->
* Retorne somente os _títulos_ dos 2 primeiros _álbuns_ cadastrados
* Retorne o _nome_ e o _email_ de todos os _usuários_ separados por ponto-e-vírgula
* Retorne _músicas_ com _duração_ entre 100 e 200 segundos
* Retorne _músicas_ que não possuem _duração_ entre 100 e 200 segundos

<!--
**Gabarito (23/02):** [spoti_pobre230223.sql](https://github.com/IgorAvilaPereira/iobd2023_1sem/blob/main/spoti_pobre230223.sql)
***
-->

* Retorne _artistas_ que possuem nome e nome artístico <!--(Dica: IS NULL)-->
* Retorne, preferencialmente, o nome de todos os artistas. Caso um determinado artista não tenha cadastrado seu nome, retorne na mesma consulta seu nome artístico <!--select coalesce(nome, nome_artistico) from artista;-->
* Retorne o título dos _álbuns_ lançados em 2023 <!--(Dica: EXTRACT(YEAR FROM data_lancamento))-->
* Retorne o _nome_ das _playlists_ que foram criadas hoje
* Atualize todos os _nomes_ dos _artistas_ (_nome_ e _nome_artistico_) para maiúsculo
* Coloque uma verificação para a coluna _duracao_ (tabela _musica_) para que toda duração tenha mais de 0 segundos
* Adicione uma restrição _UNIQUE_ para a coluna _email_ da tabela _usuario_
* Retorne somente os _artistas_ que o nome artístico começa com "Leo" (Ex: Leo Santana, Leonardo e etc.)
* Retorne o _título_ dos _álbuns_ que estão fazendo aniversário neste mês
* Retorne o _título_ dos _álbuns_ lançados no segundo semestre do ano passado (de julho de 2022 a dezembro de 2022)
* Retorne o título dos álbuns lançados nos últimos 30 dias <!-- postgres=# select current_date - interval '30 DAY';-->
  * https://www.postgresql.org/docs/current/functions-datetime.html

<!--
**Gabarito (02/03):** [spoti_pobre020323.sql](https://github.com/IgorAvilaPereira/iobd2023_1sem/blob/main/spoti_pobre230223.sql)
***
-->

* Retorne o título e o dia de lançamento (por extenso) de todos os álbuns <!-- CASE WHEN select extract(dow from current_date) -->
* Retorne o título e o mês de lançamento (por extenso) de todos os álbuns
* Retorne pelo menos um dos álbuns mais antigos
* Retorne pelo menos um dos álbuns mais recentes
* Liste os _títulos_ das _músicas_ de todos os _álbuns_ de um determinado _artista_
* Liste os _títulos_ das _músicas_ de um _álbum_ de um determinado _artista_
* Liste somente os nomes de _usuários_ que possuem alguma _playlist_ (cuidado! com a repetição)
* Liste _artistas_ que ainda não possuem _álbuns_ cadastrados
* Liste _usuários_ que ainda não possuem _playlists_ cadastradas

<!--
**Gabarito (09/03):** [spoti_pobre090323.sql](https://github.com/IgorAvilaPereira/iobd2023_1sem/blob/main/spoti_pobre090323.sql)
***
-->

* Retorne a quantidade de _álbuns_ por _artista_
* Retorne a quantidade de _músicas_ por _artista_
* Retorne o _título_ das _músicas_ de uma _playlist_ de um determinado _usuário_
* Retorne a quantidade de _playlist_ de um determinado usuário
* Retone a quantidade de _músicas_ por _artista_ (de artistas que possuem pelo menos 2 _músicas_)
* Retorne os títulos de todos os álbuns lançados no mesmo ano em que o álbum mais antigo foi lançado
* Retorne os títulos de todos os álbuns lançados no mesmo ano em que o álbum mais novo foi lançado
* Retorne na mesma consulta os nomes de todos os artistas e de todos os usuários. Caso um determinado artista não tenha cadastrado seu nome, retorne seu nome artístico
* Retorne nomes das _playlists_ com e sem _músicas_
* Retorne a média da quantidade de _músicas_ de todas as _playlists_
* Retorne somente _playlists_ que possuem quantidade de músicas maior ou igual a média

<!--
**Gabarito (16/03):** [spoti_pobre160323.sql](https://github.com/IgorAvilaPereira/iobd2023_1sem/blob/main/spoti_pobre160323.sql)
***
-->

<!--  Gabarito: ALTER TABLE musica ADD CHECK (duracao > 0); -->
