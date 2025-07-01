package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoPostgreSQL {
    private String host;
    private String username;
    private String dbname;
    private String port;
    private String password;

    // https://jdbc.postgresql.org/documentation/use/
    public ConexaoPostgreSQL(){
        this.host = "localhost";
        this.port = "5432";
        this.username = "postgres";
        this.password = "postgres";
        this.dbname = "restaurante";
    }

    public Connection getConexao(){
        String url = "jdbc:postgresql://"+this.host+":"+this.port+"/"+dbname;
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }


}
