package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoPostgreSQL {

    private String url;
    private String port;
    private String username;
    private String password;
    private String dbname;
    private String host;

    public ConexaoPostgreSQL(){
        host = "localhost";
        port = "5432";
        username = "postgres";
        password = "postgres";
        dbname = "joelho_novo";
        url = "jdbc:postgresql://"+host+":"+port+"/"+dbname;
        
    }

    public Connection getConexao(){
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}