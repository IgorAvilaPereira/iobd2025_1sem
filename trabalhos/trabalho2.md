
# Trabalho 2 – Plataforma EduLivre (Versão Reduzida)

**Valor: 5,0 pontos**

---

## Objetivo

Desenvolver um banco de dados PostgreSQL para a plataforma **EduLivre** e implementar operações básicas de consulta e manipulação em Java com JDBC.

---

## Tabelas

| Entidade      | Atributos Principais                                                    |
| ------------- | ----------------------------------------------------------------------- |
| **Usuário**   | `id (UUID)`, `nome`, `email`, `senha`, `perfil`                         |
| **Curso**     | `id (UUID)`, `titulo`, `descricao`, `data_criacao`, `avaliacao` (JSONB) |
| **Matrícula** | `id (serial)`, `usuario_id (UUID)`, `curso_id (UUID)`, `data_matricula` |
| **Conteúdo**  | `id (serial)`, `curso_id (UUID)`, `titulo`, `descricao`, `tipo`, `arquivo` (BYTEA)         |

### 📌 Exemplo de conteúdo do campo avaliacao (JSONB):

```json
{
  "media": 4.5,
  "comentarios": [
    {
      "usuario_id": "123e4567-e89b-12d3-a456-426614174000",
      "nota": 5,
      "comentario": "Ótimo curso!",
      "data": "2025-05-20"
    },
    {
      "usuario_id": "123e4567-e89b-12d3-a456-426614174001",
      "nota": 4,
      "comentario": "Bem explicado, mas poderia ter mais exemplos.",
      "data": "2025-05-21"
    }
  ]
}
```

**obs:** UUID pode ser substituído por `serial` se preferir

---

### 📌 Exemplos comuns de `tipo` de conteúdo:

| Valor de `tipo` | Descrição                          |
| --------------- | ---------------------------------- |
| `video`         | Um vídeo explicativo ou aula       |
| `pdf`           | Um documento em PDF                |
| `imagem`        | Uma imagem ilustrativa             |
| `audio`         | Um podcast ou gravação de áudio    |
| `quiz`          | Um pequeno teste ou questionário   |
| `slide`         | Uma apresentação (PowerPoint etc.) |


O campo **`tipo`** da entidade **Conteúdo** indica **o formato ou a natureza do conteúdo multimídia** que está associado a um curso. Ele ajuda a plataforma a entender como tratar o conteúdo — se deve exibir como vídeo, PDF, imagem, etc.

---

## Tarefas

### 1. Modelagem e Criação do Banco

* Crie as tabelas das 4 entidades.
* Aplique **chaves primárias e estrangeiras**, `CHECK`, `UNIQUE`.
* Use `JSONB` para avaliações e `BYTEA` para arquivos.
* Utilize **schemas** para organização (opcional).

---

### 2. Consultas e Operações com JDBC

Implemente em Java com JDBC as operações abaixo:

a) **Listar cursos, com a média de avaliação e número de alunos matriculados.**

b) **Buscar conteúdos de um curso, mostrando tipo e tamanho do arquivo.**

c) **Inserir uma nova matrícula (com validações).**

d) **Adicionar um novo comentário no JSONB de avaliação de um curso.**

---

### 3. Segurança e Controle

* Garantir que o **email do usuário seja único**.
* Perfil do usuário deve ser `'aluno'`, `'professor'` ou `'admin'`.

---

## Entregáveis

* Script SQL de criação e inserção.
* Código Java com JDBC.
* Relatório com imagens, prints e explicações.

---

## Avaliação

| Critério                                  | Pontos |
| ----------------------------------------- | ------ |
| Modelagem correta (com integridade)       | 1,5    |
| JDBC funcionando com as operações pedidas | 2,0    |
| Relatório e documentação                  | 1,0    |
| Organização geral                         | 0,5    |
