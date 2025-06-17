# Lista - DCL

#### Questão 1
**Pergunta:** Crie um usuário chamado `usuario_teste` com a senha `senha123`.

<!--
**Resposta:**
```sql
CREATE USER usuario_teste WITH PASSWORD 'senha123';
```
-->

#### Questão 2
**Pergunta:** Conceda permissão de SELECT na tabela `clientes` para o usuário `usuario_teste`.

<!--
**Resposta:**
```sql
GRANT SELECT ON TABLE clientes TO usuario_teste;
```

#### Questão 3
**Pergunta:** Revogue a permissão de SELECT na tabela `clientes` do usuário `usuario_teste`.

<!--
**Resposta:**
```sql
REVOKE SELECT ON TABLE clientes FROM usuario_teste;
```
-->

#### Questão 4
**Pergunta:** Conceda permissão de INSERT e UPDATE na tabela `produtos` para o usuário `usuario_teste`.

<!--
**Resposta:**
```sql
GRANT INSERT, UPDATE ON TABLE produtos TO usuario_teste;
```
-->
#### Questão 5
**Pergunta:** Crie um papel (role) chamado `gerente` e conceda permissão de DELETE na tabela `vendas` para esse papel.

<!--
**Resposta:**
```sql
CREATE ROLE gerente;
GRANT DELETE ON TABLE vendas TO gerente;
```
-->

#### Questão 6
**Pergunta:** Atribua o papel `gerente` ao usuário `usuario_teste`.

<!--
**Resposta:**
```sql
GRANT gerente TO usuario_teste;
```
-->

#### Questão 7
**Pergunta:** Revogue o papel `gerente` do usuário `usuario_teste`.

<!--
**Resposta:**
```sql
REVOKE gerente FROM usuario_teste;
```
-->

#### Questão 8
**Pergunta:** Conceda permissão de EXECUTE em uma função chamada `calcular_desconto` para o usuário `usuario_teste`.

<!--
**Resposta:**
```sql
GRANT EXECUTE ON FUNCTION calcular_desconto() TO usuario_teste;
```
-->

#### Questão 9
**Pergunta:** Crie um papel chamado `leitor` com permissão de SELECT em todas as tabelas do esquema `public`.

<!--
**Resposta:**
```sql
CREATE ROLE leitor;
GRANT SELECT ON ALL TABLES IN SCHEMA public TO leitor;
```
-->

#### Questão 10
**Pergunta:** Conceda permissão de USAGE no esquema `financeiro` para o usuário `usuario_teste`.

<!--
**Resposta:**
```sql
GRANT USAGE ON SCHEMA financeiro TO usuario_teste;
```
-->

#### Questão 11
**Pergunta:** Conceda permissão de USAGE e SELECT em uma sequence chamada `clientes_id_seq` para o usuário `usuario_teste`.

<!--
**Resposta:**
```sql
GRANT USAGE, SELECT ON SEQUENCE clientes_id_seq TO usuario_teste;
```
-->

#### Questão 12
**Pergunta:** Conceda permissão de UPDATE em uma sequence chamada `produtos_id_seq` para o usuário `usuario_teste`.

<!--
**Resposta:**
```sql
GRANT UPDATE ON SEQUENCE produtos_id_seq TO usuario_teste;
```
-->

#### Questão 13
**Pergunta:** Revogue a permissão de USAGE em uma sequence chamada `clientes_id_seq` do usuário `usuario_teste`.

<!--
**Resposta:**
```sql
REVOKE USAGE ON SEQUENCE clientes_id_seq FROM usuario_teste;
```
-->

#### Questão 14
**Pergunta:** Crie um papel chamado `admin` com permissão de USAGE e UPDATE em todas as sequences do esquema `public`.

<!--
**Resposta:**
```sql
CREATE ROLE admin;
GRANT USAGE, UPDATE ON ALL SEQUENCES IN SCHEMA public TO admin;
```
-->

#### Questão 15
**Pergunta:** Atribua o papel `admin` ao usuário `usuario_teste`.

<!--
**Resposta:**
```sql
GRANT admin TO usuario_teste;
```
-->

