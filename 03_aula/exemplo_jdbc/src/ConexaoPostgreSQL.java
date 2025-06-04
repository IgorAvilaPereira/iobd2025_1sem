import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoPostgreSQL {
    public Connection getConexao() throws SQLException{
     String database = "spotify_limitado";
        String user = "postgres";
        String password = "postgres";
        String host = "localhost";
        String port = "5432";
        String url = "jdbc:postgresql://"+host+":"+port+"/"+database;
        Connection conexao = DriverManager.getConnection(url, user, password);
        return conexao;  
    } 
}
