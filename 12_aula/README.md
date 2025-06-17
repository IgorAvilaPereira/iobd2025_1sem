**Exemplos:**

```sql
REVOKE privilege | ALL ON TABLE table_name |  ALL TABLES IN SCHEMA schema_name FROM role_name;
-- remove a permissão de select para todas as tabelas de um esquema
REVOKE SELECT ON ALL TABLES IN SCHEMA <schema> FROM <username>;
-- remove a permissão de select de um tabela específica
REVOKE SELECT ON <table_name> IN SCHEMA <schema> FROM <username>;
-- dá a permissão select para todas as tabelas do esquema public para o usuário elon_musk;
GRANT SELECT ON ALL TABLES IN SCHEMA public to elon_musk;
```

**Material Complementar:**

* :important https://www.devmedia.com.br/gerenciando-usuarios-e-permissoes-no-postgresql/14301

* https://phoenixnap.com/kb/postgres-create-user

* https://sqlserverguides.com/postgresql-create-user-with-password/

* https://medium.com/coding-blocks/creating-user-database-and-adding-access-on-postgresql-8bfcd2f4a91e

* https://www.postgresql.org/docs/current/app-createuser.html

* https://www.postgresql.org/docs/current/sql-createuser.html

* wikibooks.org

[Baixar todo o material da aula](https://download-directory.github.io/?url=http://github.com/IgorAvilaPereira/iobd2025_1sem/tree/main/12_aula)

&nbsp;
