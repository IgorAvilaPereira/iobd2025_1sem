package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoPostgreSQL {

    private String dbname;
    private String port;
    private String username;
    private String password;
    private String host;

    public ConexaoPostgreSQL(){
        this.host = "localhost";
        this.port = "5432";
        this.dbname = "aula2705";
        this.username = "postgres";
        this.password = "postgres";
    }

    public Connection getConnection(){
        String url = "jdbc:postgresql://"+this.host+":"+this.port+"/"+this.dbname;
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("deu xabum na conexão!");
            e.printStackTrace();
        }
        return null;
    }

}
