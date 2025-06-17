## ✅ **O que é DCL (Data Control Language)?**

DCL (Data Control Language) é um subconjunto da SQL responsável **por controlar os acessos dos usuários ao banco de dados** — ou seja, quem pode ver, alterar, ou gerenciar quais objetos (tabelas, views, schemas, funções etc.).

No PostgreSQL, os principais comandos DCL são:

* **`GRANT`** – para conceder permissões
* **`REVOKE`** – para remover permissões

---

## 🔑 **Permissões comuns no PostgreSQL**

Essas permissões podem ser aplicadas em diferentes objetos do banco:

| Permissão | Explicação                                                       |
| --------- | ---------------------------------------------------------------- |
| `SELECT`  | Permite ler dados (fazer SELECT)                                 |
| `INSERT`  | Permite inserir dados (fazer INSERT)                             |
| `UPDATE`  | Permite alterar dados existentes                                 |
| `DELETE`  | Permite excluir dados                                            |
| `USAGE`   | Permite usar um schema ou uma função                             |
| `EXECUTE` | Permite executar funções/procedures                              |
| `ALL`     | Atalho para todas as permissões relevantes daquele objeto        |
| `CONNECT` | Permite conectar-se ao banco de dados                            |
| `TEMP`    | Permite criar tabelas temporárias no banco                       |
| `CREATE`  | Permite criar objetos dentro de um schema (ex: tabelas, funções) |

---

### 🛡️ **Comandos DCL no PostgreSQL**

1. **`GRANT`** – Concede permissões a usuários ou roles.
2. **`REVOKE`** – Remove permissões concedidas anteriormente.

---

### 🔐 **Exemplos de uso no PostgreSQL**

#### 1. Criar um usuário

```sql
CREATE USER joao WITH PASSWORD 'senha123';
```

#### 2. Conceder permissões

```sql
-- Concede acesso de leitura a uma tabela
GRANT SELECT ON tabela_exemplo TO joao;

-- Concede acesso total (leitura, escrita e execução)
GRANT ALL PRIVILEGES ON DATABASE minha_base TO joao;
```

#### 3. Revogar permissões

```sql
REVOKE SELECT ON tabela_exemplo FROM joao;
```

#### 4. Conceder uso de schema

```sql
GRANT USAGE ON SCHEMA publico TO joao;
```

#### 5. Tornar um usuário um superusuário (com muito cuidado!)

```sql
ALTER USER joao WITH SUPERUSER;
```

---

### 🎯 Dicas Importantes

* Use roles (grupos de permissões) para facilitar a gestão de acessos.
* Verifique permissões com:

```sql
\z tabela_exemplo
```

no terminal `psql`.

* As permissões são acumulativas: o que for concedido a uma role será herdado por todos os usuários pertencentes a ela.

---


## 📚 **Exemplos Práticos**

### 🎯 1. Criar um usuário

```sql
CREATE USER ana WITH PASSWORD 'senha123';
```

> Isso cria um novo usuário chamado `ana`. Por padrão, ele não tem permissão para nada ainda.

---

### 🔓 2. Conceder permissão para ler uma tabela

```sql
GRANT SELECT ON tabela_clientes TO ana;
```

> Agora `ana` pode fazer `SELECT` na tabela `tabela_clientes`.

---

### ✏️ 3. Permitir leitura e escrita

```sql
GRANT SELECT, INSERT, UPDATE ON tabela_clientes TO ana;
```

> Isso permite que `ana` leia, insira e edite dados da tabela.

---

### ❌ 4. Revogar permissão

```sql
REVOKE INSERT ON tabela_clientes FROM ana;
```

> Remove a permissão de inserção.

---

### 📦 5. Permissões em nível de banco

```sql
GRANT CONNECT ON DATABASE vendas TO ana;
```

> Sem isso, `ana` não consegue nem conectar no banco de dados `vendas`.

---

### 🧱 6. Permissões em schemas

```sql
GRANT USAGE ON SCHEMA publico TO ana;
```

> Permite que `ana` use objetos dentro do schema `public`.

---

### 🧑‍🤝‍🧑 7. Usando Roles (grupos de permissões)

#### Criar uma role:

```sql
CREATE ROLE somente_leitura;
GRANT CONNECT ON DATABASE vendas TO somente_leitura;
GRANT USAGE ON SCHEMA publico TO somente_leitura;
GRANT SELECT ON ALL TABLES IN SCHEMA publico TO somente_leitura;
```

#### Atribuir essa role a um usuário:

```sql
GRANT somente_leitura TO ana;
```

> Agora `ana` herda as permissões da role `somente_leitura`.

---

## 🧠 Boas Práticas

* **Use roles para agrupar permissões**: assim você evita conceder uma por uma a cada usuário.
* **Conceda o mínimo necessário**: siga o princípio do menor privilégio.
* **Audite permissões**: veja o que está concedido com:

```sql
\z tabela_clientes
```

ou via consulta:

```sql
SELECT grantee, privilege_type
FROM information_schema.role_table_grants
WHERE table_name = 'tabela_clientes';
```


