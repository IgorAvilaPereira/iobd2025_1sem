### Dica

**Se uma coluna é `SERIAL` (ou `BIGSERIAL`)**, o PostgreSQL **cria automaticamente uma sequência associada** a essa coluna, e o **usuário que for fazer `INSERT` na tabela precisa ter permissão de uso nessa sequência**.

### 🔍 Por quê?

Quando você insere um registro em uma tabela com uma coluna `SERIAL`, o PostgreSQL usa a função `nextval('nome_da_sequencia')` para gerar automaticamente o próximo valor. Para isso, o usuário precisa de:

* `USAGE` na sequência — para poder usá-la;
* `SELECT` na sequência — para ler o valor atual (opcional, mas necessário em alguns casos, como `currval()` ou `nextval()`).

---

### ✅ Exemplo prático

Vamos dizer que você tem esta tabela:

```sql
CREATE TABLE clientes (
    id SERIAL PRIMARY KEY,
    nome TEXT
);
```

O PostgreSQL cria uma sequência oculta, normalmente com o nome:

```text
clientes_id_seq
```

Se o usuário `joao` for inserir dados nessa tabela, você precisa conceder permissões assim:

```sql
-- Permitir inserir na tabela
GRANT INSERT ON clientes TO joao;

-- Permitir uso da sequência associada
GRANT USAGE, SELECT ON SEQUENCE clientes_id_seq TO joao;
```

---

### ⚠️ Observação

Se você omitir a permissão na sequência, o `INSERT` vai falhar com um erro do tipo:

```text
ERROR: permission denied for sequence clientes_id_seq
```

---

Para descobrir o **nome da sequência criada automaticamente por uma coluna `SERIAL`** no PostgreSQL, você pode usar as seguintes abordagens:

---

### ✅ 1. **Usar `pg_get_serial_sequence()` (forma mais direta)**

Essa função retorna o nome da sequência associada a uma coluna `SERIAL`:

```sql
SELECT pg_get_serial_sequence('clientes', 'id');
```

**Exemplo de saída:**

```text
clientes_id_seq
```

---

### ✅ 2. **Consultar via `information_schema.columns`**

Se quiser procurar em várias tabelas/colunas, pode usar:

```sql
SELECT table_name, column_name, column_default
FROM information_schema.columns
WHERE column_default LIKE 'nextval(%'
  AND table_schema = 'public';
```

Isso mostra todas as colunas que usam `nextval()`, ou seja, associadas a uma sequência.

---

### ✅ 3. **Usar JOIN com `pg_class` e `pg_attrdef` (avançado)**

Se você quiser um SQL mais completo que liga tudo, aqui está:

```sql
SELECT
    t.relname AS tabela,
    a.attname AS coluna,
    d.adsrc   AS default,
    s.relname AS sequencia
FROM pg_class t
JOIN pg_attribute a ON a.attrelid = t.oid
JOIN pg_attrdef d  ON d.adrelid = t.oid AND d.adnum = a.attnum
JOIN pg_class s ON d.adsrc LIKE 'nextval(%' || s.relname || '%)'
WHERE t.relkind = 'r'
  AND d.adsrc LIKE 'nextval(%';
```

---

