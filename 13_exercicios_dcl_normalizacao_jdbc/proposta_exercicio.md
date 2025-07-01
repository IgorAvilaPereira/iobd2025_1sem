## 🧾 Exercícios - DCL, Normalização e JAVA/JDBC

**Desenvolvimento de Sistema de Cadastro de Pedidos com Acesso Seguro e Banco de Dados Normalizado**

---

## 🎯 Objetivo:

Desenvolver um sistema simples em Java para cadastro de pedidos, utilizando JDBC para interagir com um banco de dados PostgreSQL. O banco de dados será **normalizado** até a 3ª forma normal e terá o controle de acesso implementado por meio de comandos **DCL**.

---

## 🧱 Estrutura do Projeto:

### 1. **Modelagem e Normalização**

Criação do modelo lógico e físico de um sistema de pedidos contendo:

* **Cliente (id, nome, email)**
* **Produto (id, nome, preço)**
* **Pedido (id, data, id\_cliente)**
* **ItemPedido (id\_pedido, id\_produto, quantidade)**

#### ✔ Aplicação das formas normais:

* 1FN: colunas atômicas
* 2FN: nenhuma dependência parcial
* 3FN: nenhuma dependência transitiva

---

### 2. **Criação do Banco no PostgreSQL**

<!--
#### Exemplo de estrutura normalizada:

```sql
CREATE TABLE cliente (
    id SERIAL PRIMARY KEY,
    nome TEXT NOT NULL,
    email TEXT NOT NULL
);

CREATE TABLE produto (
    id SERIAL PRIMARY KEY,
    nome TEXT NOT NULL,
    preco NUMERIC(10,2) NOT NULL
);

CREATE TABLE pedido (
    id SERIAL PRIMARY KEY,
    data DATE NOT NULL,
    id_cliente INTEGER REFERENCES cliente(id)
);

CREATE TABLE item_pedido (
    id_pedido INTEGER REFERENCES pedido(id),
    id_produto INTEGER REFERENCES produto(id),
    quantidade INTEGER NOT NULL,
    PRIMARY KEY (id_pedido, id_produto)
);
```
-->
---

### 3. **Aplicação de DCL**

Criação de um usuário `usuario_app` com permissões restritas:

```sql
-- Criar usuário
CREATE USER usuario_app WITH PASSWORD 'senha123';

-- Permissões nas tabelas
GRANT CONNECT ON DATABASE restaurante TO usuario_app;
GRANT USAGE ON SCHEMA public TO usuario_app;
GRANT SELECT, INSERT, UPDATE ON cliente, produto, pedido, item_pedido TO usuario_app;

-- Permissões nas sequências
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO usuario_app;
```

### 3.1 Outras Aplicações de DCL

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



---

### 4. **Aplicação Java com JDBC**

#### Funcionalidades a implementar:

* Cadastro de cliente
* Cadastro de produto
* Criação de um novo pedido com múltiplos itens
* Consulta de pedidos por cliente

#### Exemplo de código JDBC:

```java
Connection conn = DriverManager.getConnection(url, "usuario_app", "senha123");
String sql = "INSERT INTO cliente (nome, email) VALUES (?, ?)";
PreparedStatement pstmt = conn.prepareStatement(sql);
pstmt.setString(1, "Carlos");
pstmt.setString(2, "carlos@email.com");
pstmt.executeUpdate();
```

<!--
---

## 📚 Entregáveis

1. Diagrama entidade-relacionamento (ER)
2. Tabelas SQL normalizadas (até 3FN)
3. Script SQL com DCL (criação de usuário e permissões)
4. Código-fonte Java com JDBC (CRUD básico)
5. Relatório explicando:

   * O processo de normalização
   * Justificativa das permissões DCL
   * Funcionalidade do sistema em Java

---

## 💡 Sugestões de Extensões (opcional)

* Interface gráfica (Swing/JavaFX)
* Uso de arquivos `.properties` para configuração de conexão
* Logs de auditoria com triggers
* Separação por camadas (DAO, modelo, etc.)
-->

