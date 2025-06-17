package dcl_permissao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        String sql = "INSERT INTO pessoa (nome) VALUES ('GUSTAVO');";
        String url = "jdbc:postgresql://localhost:5432/exemplo";
        
        Connection conexao = DriverManager.getConnection(url, "vai_expirar", "111");
        conexao.prepareStatement(sql).executeUpdate();
        conexao.close();
    }
}