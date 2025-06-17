## 📦 **Cenário: Sistema de Vendas**

### Tabelas principais:

* `clientes`
* `pedidos`
* `produtos`

### Tipos de usuários:

1. **admin\_vendas** – Acesso total ao banco
2. **escritor\_vendas** – Pode inserir e editar dados, mas não pode apagar ou criar objetos
3. **leitor\_vendas** – Pode apenas consultar os dados

---

## 🧑‍🤝‍🧑 **Etapas: Criação de Roles e Permissões**

### 🔹 1. Criar as roles

```sql
-- Acesso completo (para administradores)
CREATE ROLE admin_vendas;

-- Pode ler e escrever, mas não apagar
CREATE ROLE escritor_vendas;

-- Só leitura
CREATE ROLE leitor_vendas;
```

---

### 🔹 2. Criar usuários e atribuir roles

```sql
-- Criando usuários
CREATE USER maria WITH PASSWORD 'senha123';
CREATE USER joao WITH PASSWORD 'senha123';
CREATE USER ana WITH PASSWORD 'senha123';

-- Atribuindo roles
GRANT admin_vendas TO maria;
GRANT escritor_vendas TO joao;
GRANT leitor_vendas TO ana;
```

---

### 🔹 3. Conceder permissões no banco e schema

```sql
-- Permitir conexão ao banco de dados para todos os tipos
GRANT CONNECT ON DATABASE sistema_vendas TO admin_vendas, escritor_vendas, leitor_vendas;

-- Permitir uso do schema
GRANT USAGE ON SCHEMA public TO admin_vendas, escritor_vendas, leitor_vendas;
```

---

### 🔹 4. Conceder permissões nas tabelas

#### Para admin:

```sql
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO admin_vendas;
```

#### Para escritor:

```sql
GRANT SELECT, INSERT, UPDATE ON ALL TABLES IN SCHEMA public TO escritor_vendas;
```

#### Para leitor:

```sql
GRANT SELECT ON ALL TABLES IN SCHEMA public TO leitor_vendas;
```

---

### 🔹 5. Permissões em futuras tabelas (IMPORTANTE)

Quando novas tabelas forem criadas, as permissões anteriores **não são herdadas automaticamente**. Use isso para garantir isso no futuro:

```sql
-- Permitir acesso automático a novas tabelas
ALTER DEFAULT PRIVILEGES IN SCHEMA public
  GRANT SELECT ON TABLES TO leitor_vendas;

ALTER DEFAULT PRIVILEGES IN SCHEMA public
  GRANT SELECT, INSERT, UPDATE ON TABLES TO escritor_vendas;

ALTER DEFAULT PRIVILEGES IN SCHEMA public
  GRANT ALL ON TABLES TO admin_vendas;
```

---

## ✅ Resumo final:

| Usuário | Papel             | Acessos                                    |
| ------- | ----------------- | ------------------------------------------ |
| maria   | `admin_vendas`    | Total: leitura, escrita, exclusão, criação |
| joao    | `escritor_vendas` | Leitura e escrita (sem DELETE ou criação)  |
| ana     | `leitor_vendas`   | Apenas leitura                             |


