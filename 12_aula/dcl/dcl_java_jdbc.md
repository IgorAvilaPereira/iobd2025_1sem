
## ✅ Exemplo: DCL em Java com JDBC

### 🔐 Exemplo: conceder `INSERT` e uso da sequência a um usuário

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class GrantPermissionExample {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/seu_banco";
        String user = "admin";
        String password = "sua_senha";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {

            // Exemplo de comandos DCL
            String grantInsert = "GRANT INSERT ON clientes TO joao;";
            String grantSequence = "GRANT USAGE, SELECT ON SEQUENCE clientes_id_seq TO joao;";

            // Executa os comandos
            stmt.executeUpdate(grantInsert);
            stmt.executeUpdate(grantSequence);

            System.out.println("Permissões concedidas com sucesso.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

---

### ⚠️ Dicas importantes:

* Você precisa estar conectado com um usuário que **tenha privilégios de administração**, como `postgres` ou outro com `GRANT` permission.
* Comandos DCL **não são transacionais** na maioria dos SGBDs — ou seja, mesmo que você envolva em uma transação, eles não são desfeitos com `rollback`.
